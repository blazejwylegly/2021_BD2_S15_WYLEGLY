package pl.polsl.s15.library.commons.exceptions.reservations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested book is unavailable or wasn't reserved")
public class NoReservedBookException extends RuntimeException {
    public NoReservedBookException(Long serialNumber) {
        super("Reserved book with serialNumber: " + serialNumber + " does not exist");
    }
}
