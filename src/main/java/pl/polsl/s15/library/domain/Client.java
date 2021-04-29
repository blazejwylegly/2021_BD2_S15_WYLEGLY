package pl.polsl.s15.library.domain;

import pl.polsl.s15.library.domain.order.Order;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Client extends User {
    private String clientUniqueField;

    @OneToMany(mappedBy = "orderingClient")
    private List<Order> orders;
}
