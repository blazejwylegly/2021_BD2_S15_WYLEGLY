package pl.polsl.s15.library.api.controller.exception.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.polsl.s15.library.commons.exceptions.authentication.InvalidJwtException;
import pl.polsl.s15.library.commons.exceptions.InvalidRequestException;
import pl.polsl.s15.library.commons.exceptions.reservations.BooksUnavailableException;
import pl.polsl.s15.library.dtos.common.api.ErrorResponseDTO;
import pl.polsl.s15.library.api.response.ErrorResponseDTO;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidJwtException(InvalidJwtException ex) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.errorResponseBuilder()
                .status(HttpStatus.UNAUTHORIZED)
                .message("Request contains invalid JWT")
                .timestamp(new Date())
                .errorCause(ex.getMessage())
                .build();
        return prepareErrorResponse(errorResponseDTO);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidRequestException(InvalidRequestException ex) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.errorResponseBuilder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Bad request")
                .timestamp(new Date())
                .errorCause(ex.getMessage())
                .build();
        return prepareErrorResponse(errorResponseDTO);
    }
    @ExceptionHandler(BooksUnavailableException.class)
    public ResponseEntity<ErrorResponseDTO> handleBooksUnavailableException(InvalidRequestException ex) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.errorResponseBuilder()
                .status(HttpStatus.CONFLICT)
                .message("Books unavailable")
                .timestamp(new Date())
                .errorCause(ex.getMessage())
                .build();
        return prepareErrorResponse(errorResponseDTO);
    }
}
