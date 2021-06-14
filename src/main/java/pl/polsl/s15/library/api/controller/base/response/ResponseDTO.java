package pl.polsl.s15.library.api.controller.base.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseDTO {
    private HttpStatus status;
    private String message;
    private Date timestamp;
}