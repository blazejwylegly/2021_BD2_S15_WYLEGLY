package pl.polsl.s15.library.commons.exceptions.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested resrvation is unavailable")
public class NoReservationException extends RuntimeException {
    public NoReservationException(Long id) {
        super("Reservation with id: " + id + " does not exist");
    }
}
