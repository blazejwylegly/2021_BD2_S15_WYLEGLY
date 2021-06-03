package pl.polsl.s15.library.commons.exceptions.books;

public class BookAlreadyFreeException extends RuntimeException{
    public BookAlreadyFreeException(Long id){
        super("Book with id: "+  id + " is already free");
    }
}