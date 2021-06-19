package pl.polsl.s15.library.commons.exceptions.reservations;

public class NoReservationException extends RuntimeException {
    public NoReservationException(Long id) {
        super("Reservation with id: " + id + " does not exist");
    }
}
