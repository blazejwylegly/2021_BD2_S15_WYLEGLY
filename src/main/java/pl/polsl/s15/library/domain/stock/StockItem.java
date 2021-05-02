package pl.polsl.s15.library.domain.stock;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "items")
    private List<ItemPhoto> photos;

}
