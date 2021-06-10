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
@Entity(name = "stock_item")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String description;

    @ManyToMany
    @JoinTable(
            name = "items_photos",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    protected List<ItemPhoto> photos;

    public StockItem(String desc)
    {
        this.description = desc;
    }
}
