package pl.polsl.s15.library.commons.exceptions.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Book with selected serial number already exists")
public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(Long id){
        super("Book with serial number: "+  id + " already exists");
    }
}
