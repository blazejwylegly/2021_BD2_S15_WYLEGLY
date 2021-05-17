package pl.polsl.s15.library.exception;

public class NoSuchBookException extends RuntimeException{
    public NoSuchBookException(Long id){
        super("Book with id: "+  id + " doesnt exist");
    }
}
