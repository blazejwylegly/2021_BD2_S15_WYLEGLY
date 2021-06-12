package pl.polsl.s15.library.domain.ordering;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.user.Client;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "cart")
    private Client client;

    public Cart(Client client)
    {
        this.client = client;
    }
    public void addOrderItem(OrderItem item)
    {
        orderItems.add(item);
    }
    public void removeOrderItem(OrderItem item) {
        orderItems.remove(item);
    }
    public void updateOrderItem(OrderItem item){
        orderItems.stream().
                filter(p -> p.getId().equals(item.getId())).
                forEach(p -> p.setRequestedEndDate(item.getRequestedEndDate()));
    }
    public void clearItems() {
        orderItems.clear();
    }
}

