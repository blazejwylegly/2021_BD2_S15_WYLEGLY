package pl.polsl.s15.library.dtos.stock.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class BookBasicDTO {
    private Long bookId;
    private String name;
    private String author;
    private List<String> urls;
    private Boolean isOccupied;
    private Integer numberOfBooks;
    private Optional<Long> numberOfOccupiedBooks;
}
