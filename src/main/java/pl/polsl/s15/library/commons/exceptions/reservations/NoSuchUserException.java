package pl.polsl.s15.library.commons.exceptions.reservations;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(Long id){
        super("User with id: "+  id + " does not exist");
    }
}