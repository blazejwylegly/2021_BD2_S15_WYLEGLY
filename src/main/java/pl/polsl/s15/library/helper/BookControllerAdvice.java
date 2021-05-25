package pl.polsl.s15.library.helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.polsl.s15.library.exception.NoSuchBookException;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(NoSuchBookException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoSuchBookException(NoSuchBookException e){
        return e.getMessage();
    }
}
