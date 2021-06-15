package pl.polsl.s15.library.api.controller.authorities.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;

import java.util.Date;
import java.util.Set;

public class GetAuthoritiesResponseDTO extends ResponseDTO {
    private Set<String> authorities;

    @Builder(builderMethodName = "getAuthoritiesResponseBuilder")
    public GetAuthoritiesResponseDTO(HttpStatus status,
                                     String message,
                                     Date timestamp,
                                     Set<String> authorities) {
        super(status, message, timestamp);
        this.authorities = authorities;
    }
}
