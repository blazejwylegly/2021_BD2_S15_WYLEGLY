package pl.polsl.s15.library.dtos.stock.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class BookFullDTO {
    private Long bookId;
    private Optional<Long> serialNumber;
    private String name;
    private String author;
    private Optional<String> publisher;
//    private List<String> urls;
    private String description;
    private Optional<LocalDate> publicationDate;
    private Boolean isOccupied;
    private Integer numberOfBooks;
    private Optional<Long> numberOfOccupiedBooks;
}

