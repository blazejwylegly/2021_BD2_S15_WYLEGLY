package pl.polsl.s15.library.domain.stock.books;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.stock.StockItem;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends StockItem {

    @ManyToOne
    @JoinColumn(name = "details_id")
    private BookDetails details;
}
