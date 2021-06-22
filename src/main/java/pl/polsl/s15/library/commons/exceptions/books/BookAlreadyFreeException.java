package pl.polsl.s15.library.commons.exceptions.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Some books are already free")
public class BookAlreadyFreeException extends RuntimeException{
    public BookAlreadyFreeException(Long id){
        super("Book with id: "+  id + " is already free");
    }
}