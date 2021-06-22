package pl.polsl.s15.library.api.controller.authorities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.polsl.s15.library.api.controller.base.response.ErrorResponseDTO;
import pl.polsl.s15.library.commons.exceptions.user.permissions.AuthorityAlreadyExistsException;

import java.util.Date;

@RestControllerAdvice
public class AuthorityControllerExceptionHandler {


    @ExceptionHandler(AuthorityAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthorityAlreadyExistsException(
            AuthorityAlreadyExistsException exception
    ) {
        ErrorResponseDTO response = authorityCreationFailedResponse(exception);
        return ResponseEntity.status(response.getStatus())
                .body(response);
    }

    private ErrorResponseDTO authorityCreationFailedResponse(
            AuthorityAlreadyExistsException exception
    ) {
        return ErrorResponseDTO.errorResponseBuilder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Failed to create new authority!")
                .errorCause(exception.getMessage())
                .timestamp(new Date())
                .build();
    }
}
