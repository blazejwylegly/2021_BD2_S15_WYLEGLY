package pl.polsl.s15.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Book with this id doesnt exist")
public class NoSuchBookException extends RuntimeException{
    public NoSuchBookException(Long id){
        super("Book with id: "+  id + " doesnt exist");
    }
}
