package pl.polsl.s15.library.commons.exceptions.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Some books are already occupied")
public class BookAlreadyOccupiedException extends RuntimeException{
    public BookAlreadyOccupiedException(Long id){
        super("Book with id: "+  id + " is already occupied");
    }
}