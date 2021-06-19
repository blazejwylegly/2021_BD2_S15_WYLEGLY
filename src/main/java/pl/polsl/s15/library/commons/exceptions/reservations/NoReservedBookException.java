package pl.polsl.s15.library.commons.exceptions.reservations;

public class NoReservedBookException extends RuntimeException {
    public NoReservedBookException(Long serialNumber) {
        super("Reserved book with serialNumber: " + serialNumber + " does not exist");
    }
}
