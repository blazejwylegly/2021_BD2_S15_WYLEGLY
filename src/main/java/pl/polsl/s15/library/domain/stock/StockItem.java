package pl.polsl.s15.library.domain.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "items_photos",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    private List<ItemPhoto> photos;
    public StockItem(String desc)
    {
        this.description = desc;
    }
    public StockItem(StockItem item)
    {
        this.id = item.id;
        this.description = item.description;
        this.photos = item.photos;
    }
}
