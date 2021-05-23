package pl.polsl.s15.library.controller.login;

import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;

@Component
public class AuthResponseMapper {
    public static AuthResponse authRequestSuccessful(User user) {
        return AuthResponse.of(user.getUsername(), "Authentication successful");
    }
}
