package pl.polsl.s15.library.controller.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.commons.exceptions.InvalidRequestException;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.dtos.login.AuthDTOMapper;
import pl.polsl.s15.library.dtos.login.AuthRequestDTO;
import pl.polsl.s15.library.dtos.login.AuthResponseDTO;
import pl.polsl.s15.library.service.UserService;
import pl.polsl.s15.library.commons.utils.JwtUtility;


import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class LoginController {

    private final JwtUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final AuthDTOMapper dtoMapper;
    private final UserService userService;

    public LoginController(JwtUtility jwtUtility,
                           AuthenticationManager authenticationManager,
                           AuthDTOMapper dtoMapper,
                           UserService userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.dtoMapper = dtoMapper;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO request, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new InvalidRequestException("Invalid authentication request body!");

        AuthResponseDTO authResponseDTO = authenticate(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authResponseDTO);
    }

    private AuthResponseDTO authenticate(AuthRequestDTO request) {
        Authentication auth = retrieveAuthenticationPrincipal(request);
        Date issuedAt = new Date();
        User user = (User) auth.getPrincipal();
        String accessToken = jwtUtility.generateAccessToken(user, issuedAt);
        return dtoMapper.authRequestSuccessful(user, accessToken);
    }

    private Authentication retrieveAuthenticationPrincipal(AuthRequestDTO request) {
        Optional<Authentication> emailAuth = retrieveEmailBasedAuthentication(request);
        if (emailAuth.isPresent()) {
            return emailAuth.get();
        }

        Optional<Authentication> usernameAuth = retrieveUsernameBasedAuthentication(request);
        if (usernameAuth.isPresent()) {
            return usernameAuth.get();
        }

        throw new BadCredentialsException("Invalid username and email address provided!");
    }

    private Optional<Authentication> retrieveUsernameBasedAuthentication(AuthRequestDTO request) {
        return Optional.ofNullable(request.getUsername())
                .map(
                        username -> authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(username, request.getPassword())
                        )
                );
    }

    private Optional<Authentication> retrieveEmailBasedAuthentication(AuthRequestDTO request) {
        Optional<User> userByEmail =  Optional.ofNullable(request.getEmail())
                .map(userService::loadUserByEmail)
                .orElse(Optional.empty());

        return userByEmail.map(
                user -> authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword())
                )
        );
    }
}
