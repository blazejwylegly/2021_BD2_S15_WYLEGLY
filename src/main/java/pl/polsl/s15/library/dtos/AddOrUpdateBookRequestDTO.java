package pl.polsl.s15.library.dtos;

import lombok.Setter;
import pl.polsl.s15.library.domain.stock.books.Book;
import pl.polsl.s15.library.domain.stock.books.BookDetails;
import pl.polsl.s15.library.domain.stock.books.RentalBook;

import java.time.LocalDate;

@Setter
public class AddOrUpdateBookRequestDTO {
    Long id;
    String[] photos;
    String author;
    String title;
    Boolean available;
    String publisher;
    LocalDate publicationDate;
    String description;

    public RentalBook getRentalBook() {
        BookDetails details = new BookDetails(title, author, publisher, publicationDate);
        return new RentalBook(details, description, id);
    }

    public long getSerialNumber() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BookDetails getBookDetails() {
        return new BookDetails(title, author, publisher, publicationDate);
    }

    public boolean invalidAdd() {
        return title == null || author == null || description == null || id == null;
    }

    public boolean invalidUpdate() {
        return id == null;
    }
}
