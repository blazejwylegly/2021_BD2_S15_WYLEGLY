package pl.polsl.s15.library.api.response.permissions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.response.ResponseDTO;

import java.util.Date;
import java.util.Set;

@Data
public class AuthoritiesResponseDTO extends ResponseDTO {

    private Set<String> authorities;

    @Builder(builderMethodName = "authoritiesResponseBuilder")
    public AuthoritiesResponseDTO(HttpStatus status,
                                  String message,
                                  Date timestamp,
                                  Set<String> authorities) {
        super(status, message, timestamp);
        this.authorities = authorities;
    }
}
