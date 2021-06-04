package pl.polsl.s15.library.dtos.login;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;

import java.util.Date;

@Component
public class AuthDTOMapper {
    public AuthResponseDTO authRequestSuccessful(User user, String accessToken) {
        return AuthResponseDTO.authResponseBuilder()
                .status(HttpStatus.OK)
                .message("Authentication successful!")
                .accessToken(accessToken)
                .timestamp(new Date())
                .build();
    }

    public AuthResponseDTO authRequestFailed(AuthRequestDTO request, String errorMessage) {
        return AuthResponseDTO.authResponseBuilder()
                .message(errorMessage)
                .build();
    }
}
