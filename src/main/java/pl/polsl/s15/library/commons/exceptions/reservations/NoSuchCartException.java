package pl.polsl.s15.library.commons.exceptions.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested cart is unavailable")
public class NoSuchCartException extends RuntimeException {
    public NoSuchCartException(Long id){
        super("Cart with id: "+  id + " does not exist");
    }
}