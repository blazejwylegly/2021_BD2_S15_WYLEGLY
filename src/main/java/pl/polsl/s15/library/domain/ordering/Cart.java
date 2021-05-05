package pl.polsl.s15.library.domain.ordering;

import pl.polsl.s15.library.domain.user.Client;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long sessionId;

    @OneToMany(mappedBy = "cart")
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "cart")
    private Client client;
}

