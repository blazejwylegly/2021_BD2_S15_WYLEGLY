package pl.polsl.s15.library.domain.deliveries;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "deliveries")
public class Delivery  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "delivery",fetch = FetchType.LAZY)
    private List<DeliveryArticle> orderedItems;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column(name = "delivery_request_date")
    private LocalDateTime deliveryRequestDate;

    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate;
}
