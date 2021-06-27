package pl.polsl.s15.library.api.controller.base.request;

import lombok.Setter;
import pl.polsl.s15.library.domain.stock.books.BookDetails;
import pl.polsl.s15.library.domain.stock.books.RentalBook;

import java.time.LocalDate;

@Setter
public class AddOrUpdateBookRequestDTO {
    Long serial_number;
    String[] photos;
    String author;
    String title;
    Boolean available;
    String publisher;
    LocalDate publicationDate;
    String description;

    public RentalBook getRentalBook() {
        BookDetails details = new BookDetails(title, author, publisher, publicationDate);
        return new RentalBook(details, description, serial_number);
    }

    public long getSerialNumber() {
        return serial_number;
    }

    public String getDescription() {
        return description;
    }

    public BookDetails getBookDetails() {
        return new BookDetails(title, author, publisher, publicationDate);
    }

    public boolean invalidAdd() {
        return title == null || author == null || description == null || serial_number == null;
    }

    public boolean invalidUpdate() {
        return serial_number == null;
    }
}
