package pl.polsl.s15.library.controller.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private final AuthResponseMapper authResponseMapper;
    private final UserService userService;

    public LoginController(JwtUtility jwtUtility,
                           AuthenticationManager authenticationManager,
                           AuthResponseMapper authResponseMapper,
                           UserService userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.authResponseMapper = authResponseMapper;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            return attemptAuthentication(request);
        } catch (Exception ex) {
            throw new InvalidCredentialsException("Invalid credentials provided" + ex.getMessage());
        }
    }

    private ResponseEntity<AuthResponse> attemptAuthentication(AuthRequest request) {
        Authentication auth = retrievePrioritizedAuthenticationPrincipal(request);
        Date issuedAt = new Date();
        User user = (User) auth.getPrincipal();
        String accessToken = jwtUtility.generateAccessToken(user, issuedAt);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .body(AuthResponseMapper.authRequestSuccessful(user));
    }

    private Authentication retrievePrioritizedAuthenticationPrincipal(AuthRequest request) throws BadCredentialsException{
        Optional<Authentication> emailAuth = retrieveEmailBasedAuthentication(request);
        Optional<Authentication> usernameAuth = retrieveUsernameBasedAuthentication(request);

        if(emailAuth.isPresent())
            return emailAuth.get();
        else if (usernameAuth.isPresent())
            return usernameAuth.get();
        else
            throw new BadCredentialsException("Invalid username and email address provided!");
    }

    private Optional<Authentication> retrieveUsernameBasedAuthentication(AuthRequest request) {
        return request.getUsername().map(
                username -> authenticationManager.authenticate(
                        authAttemptPrincipal(username, request.getPassword())
                )
        );
    }

    private Optional<Authentication> retrieveEmailBasedAuthentication(AuthRequest request) {
        Optional<User> optionalUser = request.getEmail()
                .map(userService::loadUserByEmail)
                .orElse(Optional.empty());

        return optionalUser.map(
                user -> authenticationManager.authenticate(
                        authAttemptPrincipal(user.getUsername(), request.getPassword())
                )
        );
    }

    private UsernamePasswordAuthenticationToken authAttemptPrincipal(String username, String password) {
        return new UsernamePasswordAuthenticationToken(
                username, password
        );
    }

    @AllArgsConstructor
    @Getter
    private enum PrimaryAuthType {
        EMAIL("EMAIL"),
        USERNAME("USERNAME");
        private String authType;
    }
}
