package pl.polsl.s15.library.commons.exceptions.reservations;

public class UnassignedCartException extends RuntimeException {
    public UnassignedCartException(Long cartID) {
        super("Cart with id: " + cartID + " is not assigned to any user");
    }
}
