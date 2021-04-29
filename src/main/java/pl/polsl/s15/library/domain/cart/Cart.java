package pl.polsl.s15.library.domain.cart;

import pl.polsl.s15.library.domain.orderable.Orderable;
import pl.polsl.s15.library.domain.strategy.IdStrategyClass;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "carts")
public class Cart extends IdStrategyClass {

    @ManyToMany
    @JoinTable(
            name = "carts_orderables",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "orderable_id"))
    private List<Orderable> cartOrderables = new ArrayList<>();
}
