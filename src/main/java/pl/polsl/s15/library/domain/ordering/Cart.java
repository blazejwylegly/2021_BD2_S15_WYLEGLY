package pl.polsl.s15.library.domain.ordering;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<OrderItem> orderItems;

    public Cart()
    {
        this.orderItems = new ArrayList<>();
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

