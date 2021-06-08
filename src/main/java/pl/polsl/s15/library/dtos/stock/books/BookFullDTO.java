package pl.polsl.s15.library.dtos.stock.books;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookFullDTO {
    private Long bookId;
    private Long serialNumber;
    private String name;
    private String author;
    private String publisher;
    //    private List<String> urls;
    private String description;
    private LocalDate publicationDate;
    private Boolean isOccupied;
    private Long numberOfBooks;
    private Long numberOfOccupiedBooks;
}

