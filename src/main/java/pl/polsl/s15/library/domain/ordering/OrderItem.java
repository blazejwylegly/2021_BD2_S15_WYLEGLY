package pl.polsl.s15.library.domain.ordering;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(nullable = false, name = "requested_start_date")
    private LocalDateTime requestedStartDate;

    @Column(nullable = false, name = "requested_end_date")
    private LocalDateTime requestedEndDate;
}
