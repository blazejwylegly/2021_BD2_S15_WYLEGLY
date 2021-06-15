package pl.polsl.s15.library.api.controller.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.polsl.s15.library.commons.exceptions.authentication.UserAlreadyRegisteredException;
import pl.polsl.s15.library.api.response.ErrorResponseDTO;

import java.util.Date;

@RestControllerAdvice
public class RegistrationControllerExceptionHandler {

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyRegistered(UserAlreadyRegisteredException exception) {
        ErrorResponseDTO responseDTO = userRegistrationFailed(exception);
        return ResponseEntity.status(responseDTO.getStatus())
                .body(responseDTO);
    }

    private ErrorResponseDTO userRegistrationFailed(UserAlreadyRegisteredException exception) {
        return ErrorResponseDTO.errorResponseBuilder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Failed to register new user!")
                .errorCause(exception.getMessage())
                .timestamp(new Date())
                .build();
    }
}
