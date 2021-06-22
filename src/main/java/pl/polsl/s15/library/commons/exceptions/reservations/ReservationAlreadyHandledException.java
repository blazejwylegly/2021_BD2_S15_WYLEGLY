package pl.polsl.s15.library.commons.exceptions.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Requested reservation was already handled")
public class ReservationAlreadyHandledException extends RuntimeException {
    public ReservationAlreadyHandledException(Long id){
        super("Reservation with id: "+  id + " was already handled");
    }
}
