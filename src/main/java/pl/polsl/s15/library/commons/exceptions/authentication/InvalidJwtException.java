package pl.polsl.s15.library.commons.exceptions.authentication;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
