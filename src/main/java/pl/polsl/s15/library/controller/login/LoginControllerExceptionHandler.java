package pl.polsl.s15.library.controller.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.polsl.s15.library.controller.exception.handling.BaseExceptionHandler;
import pl.polsl.s15.library.dtos.common.api.ErrorResponseDTO;

import java.util.Date;

@RestControllerAdvice
public class LoginControllerExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidCredentials(BadCredentialsException exception) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.errorResponseBuilder()
                .message("Invalid credentials provided!")
                .status(HttpStatus.UNAUTHORIZED)
                .timestamp(new Date())
                .errorCause(exception.getMessage())
                .build();
        return prepareErrorResponse(errorResponseDTO);
    }
}
