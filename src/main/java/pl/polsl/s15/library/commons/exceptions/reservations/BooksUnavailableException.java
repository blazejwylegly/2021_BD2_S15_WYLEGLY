package pl.polsl.s15.library.commons.exceptions.reservations;

import java.util.List;

public class BooksUnavailableException extends RuntimeException{
    public BooksUnavailableException(String unavailable){
        super("Books with id's: "+  unavailable + " are no longer available");
    }
}
