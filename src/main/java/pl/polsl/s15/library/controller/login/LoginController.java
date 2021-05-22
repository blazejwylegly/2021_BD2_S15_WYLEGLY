package pl.polsl.s15.library.controller.login;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.security.jwt.JwtUtility;

import java.util.Date;

@RestController
@RequestMapping("/api/public")
public class LoginController {

    private final JwtUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final AuthResponseMapper authResponseMapper;

    @Autowired
    public LoginController(JwtUtility jwtUtility, AuthenticationManager authenticationManager, AuthResponseMapper authResponseMapper) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.authResponseMapper = authResponseMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            return attemptAuthentication(request);
        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialsException("Invalid credentials provided");
        }
    }

    private ResponseEntity<AuthResponse> attemptAuthentication(AuthRequest request) throws BadCredentialsException {
        Authentication auth = authenticationManager.authenticate(
                authAttemptPrincipal(request.getUsername(), request.getPlainPassword())
        );

        Date issuedAt = new Date();
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.AUTHORIZATION,
                        jwtUtility.generateAccessToken(user, issuedAt)
                )
                .body(AuthResponseMapper.authRequestSuccessful(user, issuedAt));
    }

    private UsernamePasswordAuthenticationToken authAttemptPrincipal(String username, String password) {
        return new UsernamePasswordAuthenticationToken(
                username, password
        );
    }

}
