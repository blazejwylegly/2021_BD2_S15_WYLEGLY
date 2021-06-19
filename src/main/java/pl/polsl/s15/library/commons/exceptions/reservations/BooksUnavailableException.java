package pl.polsl.s15.library.commons.exceptions.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Some books are unavailable")
public class BooksUnavailableException extends RuntimeException{
    public BooksUnavailableException(String unavailable){
        super("Books with id's: "+  unavailable + " are no longer available");
    }
    public BooksUnavailableException(String unavailable, Throwable t){
        super("Books with id's: "+  unavailable + " are no longer available",t);
    }
}
