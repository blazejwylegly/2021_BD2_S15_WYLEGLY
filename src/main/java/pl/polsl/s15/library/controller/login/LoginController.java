package pl.polsl.s15.library.controller.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.controller.login.dto.AuthDTOMapper;
import pl.polsl.s15.library.controller.login.dto.AuthRequestDTO;
import pl.polsl.s15.library.controller.login.dto.AuthResponseDTO;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.security.jwt.JwtUtility;
import pl.polsl.s15.library.service.UserService;

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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(authenticationSuccessResponse(request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(authenticationFailedResponse(request, ex.getMessage()));
        }
    }

    private AuthResponseDTO authenticationSuccessResponse(AuthRequestDTO request) {
        Authentication auth = retrieveAnyAuthenticationPrincipal(request);
        Date issuedAt = new Date();
        User user = (User) auth.getPrincipal();
        String accessToken = jwtUtility.generateAccessToken(user, issuedAt);
        return dtoMapper.authRequestSuccessful(user, accessToken);
    }

    private AuthResponseDTO authenticationFailedResponse(AuthRequestDTO request, String errorMessage) {
        return dtoMapper.authRequestFailed(request, errorMessage);
    }

    private Authentication retrieveAnyAuthenticationPrincipal(AuthRequestDTO request) {
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
        return request.getUsername().map(
                username -> authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, request.getPassword())
                )
        );
    }

    private Optional<Authentication> retrieveEmailBasedAuthentication(AuthRequestDTO request) {
        Optional<User> optionalUser = request.getEmail()
                .map(userService::loadUserByEmail)
                .orElse(Optional.empty());

        return optionalUser.map(
                user -> authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
                )
        );
    }
}
