package pl.polsl.s15.library.dtos.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.dtos.common.api.ResponseDTO;

import java.util.Date;


@AllArgsConstructor
@Getter
public class AuthResponseDTO extends ResponseDTO {
    private String accessToken;

    @Builder(builderMethodName = "authResponseBuilder")
    public AuthResponseDTO(HttpStatus status, String message, Date timestamp, String accessToken) {
        super(status, message, timestamp);
        this.accessToken = accessToken;
    }
}
