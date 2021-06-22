package pl.polsl.s15.library.commons.exceptions.delivery;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(String message) {
        super(message);
    }
}
