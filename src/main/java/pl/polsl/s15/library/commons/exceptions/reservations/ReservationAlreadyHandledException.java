package pl.polsl.s15.library.commons.exceptions.reservations;

public class ReservationAlreadyHandledException extends RuntimeException {
    public ReservationAlreadyHandledException(Long id){
        super("Reservation with id: "+  id + " was already handled");
    }
}
