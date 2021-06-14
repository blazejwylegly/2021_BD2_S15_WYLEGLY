package pl.polsl.s15.library.commons.exceptions.user.permissions;

public class AuthorityAlreadyExistsException extends RuntimeException {
    public AuthorityAlreadyExistsException(String message) {
        super(message);
    }
}
