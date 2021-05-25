package pl.polsl.s15.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "Book is already free")
public class BookAlreadyFreeException extends RuntimeException{
    public BookAlreadyFreeException(Long id){
        super("Book with id: "+  id + " is already free");
    }
}