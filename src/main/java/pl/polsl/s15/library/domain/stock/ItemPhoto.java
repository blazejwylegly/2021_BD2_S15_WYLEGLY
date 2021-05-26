package pl.polsl.s15.library.domain.stock;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photos")
public class ItemPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToMany
    @JoinTable(
            name = "items_photos",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<StockItem> items;
}
