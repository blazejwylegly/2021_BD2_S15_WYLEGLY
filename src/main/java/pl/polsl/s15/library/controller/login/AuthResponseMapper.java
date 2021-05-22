package pl.polsl.s15.library.controller.login;

import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;

import java.util.Date;

@Component
public class AuthResponseMapper {
    public static AuthResponse authRequestSuccessful(User user, Date date) {
        return AuthResponse.of(user.getUsername(), "Authentication successful", date);
    }
}
