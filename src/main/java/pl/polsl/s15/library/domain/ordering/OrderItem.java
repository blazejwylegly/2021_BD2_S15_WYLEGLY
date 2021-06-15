package pl.polsl.s15.library.domain.ordering;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    //@Column(nullable = false, name = "requested_start_date")
    //private LocalDateTime requestedStartDate;

    @Column(nullable = false, name = "requested_end_date")
    private LocalDate requestedEndDate;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public OrderItem(long itemId, LocalDate endDate, Cart cart)
    {
        this.itemId = itemId;
        requestedEndDate = endDate;
        this.cart = cart;
    }
}
