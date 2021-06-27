package pl.polsl.s15.library.api.controller.base.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class ErrorResponseDTO extends ResponseDTO {
    private String errorCause;
    private String hints;

    @Builder(builderMethodName = "errorResponseBuilder")
    public ErrorResponseDTO(HttpStatus status, String message, Date timestamp, String errorCause, String hints) {
        super(status, message, timestamp);
        this.errorCause = errorCause;
        this.hints = hints;
    }
}
