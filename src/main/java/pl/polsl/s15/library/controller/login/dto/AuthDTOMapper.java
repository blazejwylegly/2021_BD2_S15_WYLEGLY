package pl.polsl.s15.library.controller.login.dto;

import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;

@Component
public class AuthDTOMapper {
    public AuthResponseDTO authRequestSuccessful(User user, String accessToken) {
        return AuthResponseDTO.builder()
                .message("Authentication successful!")
                .accessToken(accessToken)
                .build();
    }

    public AuthResponseDTO authRequestFailed(AuthRequestDTO request, String errorMessage) {
        return AuthResponseDTO.builder()
                .message(errorMessage)
                .build();
    }
}
