package pl.polsl.s15.library.commons.exceptions.reservations;

public class NoSuchCartException extends RuntimeException {
    public NoSuchCartException(Long id){
        super("Cart with id: "+  id + " does not exist");
    }
}