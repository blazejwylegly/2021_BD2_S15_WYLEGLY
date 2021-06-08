package pl.polsl.s15.library.domain.stock.books;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.s15.library.domain.stock.ArticleDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
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

    public BookDetails(String name, String author, String publisher, LocalDate publicationDate) {
        this.name = name;
        this.author = author;
        if (publisher != null)
            this.publisher = publisher;
        if (publicationDate != null)
            this.publicationDate = publicationDate;
    }

    public BookDetails(BookDetails details) {
        this.name = details.name;
        this.author = details.author;
        this.publicationDate = details.publicationDate;
        this.publisher = details.publisher;
    }

    public Long getId() {
        return super.getId();
    }
}
