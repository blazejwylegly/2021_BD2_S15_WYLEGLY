package pl.polsl.s15.library.commons.exceptions;

public class NoSuchBookException extends RuntimeException{
    public NoSuchBookException(Long id){
        super("Book with id: "+  id + " doesnt exist");
    }
}
