package pl.polsl.s15.library.controller.login.dto;

import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;

@Component
public class AuthDTOMapper {
    public static AuthResponseDTO authRequestSuccessful(User user) {
        return AuthResponseDTO.of(user.getUsername(), "Authentication successful");
    }
}
