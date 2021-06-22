package pl.polsl.s15.library.commons.exceptions.reservations;

import java.util.List;

public class BooksUnavailableHelperException extends RuntimeException{
    List<Long> ids;
    public BooksUnavailableHelperException(String unavailable, List ids){
        super("Books with id's: "+  unavailable + " are no longer available");
        this.ids=ids;
    }
    public List<Long> getIds(){return ids;}
}
