package pl.polsl.s15.library.api.controller.login.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;

import java.util.Date;

@AllArgsConstructor
@Getter
public class LoginResponseDTO extends ResponseDTO {
    private String accessToken;

    @Builder(builderMethodName = "authResponseBuilder")
    public LoginResponseDTO(HttpStatus status, String message, Date timestamp, String accessToken) {
        super(status, message, timestamp);
        this.accessToken = accessToken;
    }
}
