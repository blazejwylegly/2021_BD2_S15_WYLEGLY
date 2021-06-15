package pl.polsl.s15.library.domain.ordering;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(nullable = false, name = "requested_end_date")
    private LocalDate requestedEndDate;
}
