package pl.polsl.s15.library.commons.exceptions.reservations;

public class NoCartException extends RuntimeException{
    public NoCartException(Long id){
            super("User with id: "+  id + " have no cart assigned");
        }
}
