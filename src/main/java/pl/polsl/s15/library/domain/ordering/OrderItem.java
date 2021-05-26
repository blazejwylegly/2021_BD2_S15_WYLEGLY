package pl.polsl.s15.library.domain.ordering;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long itemId;

    @Column(nullable = false)
    private LocalDateTime requestedStartDate;

    @Column(nullable = false)
    private LocalDateTime requestedEndDate;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
