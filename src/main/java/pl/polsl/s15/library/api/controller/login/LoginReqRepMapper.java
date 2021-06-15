package pl.polsl.s15.library.api.controller.login;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.api.controller.login.response.LoginResponseDTO;

import java.util.Date;

@Component
public class LoginReqRepMapper {
    public LoginResponseDTO authRequestSuccessful(User user, String accessToken) {
        return LoginResponseDTO.authResponseBuilder()
                .status(HttpStatus.OK)
                .message("Authentication successful!")
                .accessToken(accessToken)
                .timestamp(new Date())
                .build();
    }
}
