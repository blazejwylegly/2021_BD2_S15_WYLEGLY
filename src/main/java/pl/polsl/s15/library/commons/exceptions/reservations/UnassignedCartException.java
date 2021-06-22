package pl.polsl.s15.library.commons.exceptions.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested cart is not assigned to any user")
public class UnassignedCartException extends RuntimeException {
    public UnassignedCartException(Long cartID) {
        super("Cart with id: " + cartID + " is not assigned to any user");
    }
}
