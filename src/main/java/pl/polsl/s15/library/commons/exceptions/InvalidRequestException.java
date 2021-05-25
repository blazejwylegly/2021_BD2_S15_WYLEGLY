package pl.polsl.s15.library.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidRequestException extends ServletException {
    public InvalidRequestException(String msg) {
        super(msg);
    }
}

