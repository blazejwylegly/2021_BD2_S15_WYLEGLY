package pl.polsl.s15.library.commons.exceptions.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Some books are unavailable")
public class BooksUnavailableException extends RuntimeException{
    public BooksUnavailableException(String message){
        super(message);
    }
    public BooksUnavailableException(String message, Throwable t){
        super(message,t);
    }
}
