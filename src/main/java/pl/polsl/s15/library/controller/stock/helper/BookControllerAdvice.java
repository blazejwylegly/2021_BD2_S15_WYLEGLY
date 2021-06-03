package pl.polsl.s15.library.controller.stock.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.polsl.s15.library.commons.exceptions.books.NoSuchBookException;


@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(NoSuchBookException.class)
    @ResponseBody
    public ResponseEntity<?> handleNoSuchBookException(NoSuchBookException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
