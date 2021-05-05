package pl.polsl.s15.library.domain.stock.books;

import pl.polsl.s15.library.domain.stock.ArticleDetails;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book_details")
public class BookDetails extends ArticleDetails {

    public String name;
    public String author;
    public String publisher;
    public LocalDate publicationDate;

    @OneToMany(mappedBy = "details")
    private List<Book> books;
}
