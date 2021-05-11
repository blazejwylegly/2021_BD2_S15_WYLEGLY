package pl.polsl.s15.library.domain.stock.books;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.stock.ArticleDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "book_details")
public class BookDetails extends ArticleDetails {

    private String name;
    private String author;
    private String publisher;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @OneToMany(mappedBy = "details")
    private List<Book> books = new ArrayList<>();
}
