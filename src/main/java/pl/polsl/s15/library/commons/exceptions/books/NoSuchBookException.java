package pl.polsl.s15.library.commons.exceptions.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested book is unavailable")
public class NoSuchBookException extends RuntimeException{
    public NoSuchBookException(Long id){
        super("Book with id: "+  id + " doesn't exist");
    }
}
