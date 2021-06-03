package pl.polsl.s15.library.commons.exceptions.books;

public class BookAlreadyOccupiedException extends RuntimeException{
    public BookAlreadyOccupiedException(Long id){
        super("Book with id: "+  id + " is already occupied");
    }
}