package pl.polsl.s15.library.domain.stock.books;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.polsl.s15.library.domain.stock.ArticleDetails;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "book_details")
public class BookDetails extends ArticleDetails {

    public String name;
    public String author;
    public String publisher;
    public LocalDate publicationDate;

    @OneToMany(mappedBy = "details", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
    public BookDetails(String name, String author, String publisher, LocalDate publicationDate)
    {
        this.name = name;
        this.author = author;
        if(publisher!=null)
            this.publisher = publisher;
        if(publicationDate!=null)
            this.publicationDate = publicationDate;
    }
    public BookDetails(BookDetails details)
    {
        this.name = details.name;
        this.author = details.author;
        this.publicationDate = details.publicationDate;
        this.publisher = details.publisher;
    }
    public Long getId()
    {
        return super.getId();
    }
}
