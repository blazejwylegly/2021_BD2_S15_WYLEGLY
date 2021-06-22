package pl.polsl.s15.library.commons.exceptions.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested cart is unavailable")
public class NoCartException extends RuntimeException{
    public NoCartException(Long id){
            super("User with id: "+  id + " have no cart assigned");
        }
}
