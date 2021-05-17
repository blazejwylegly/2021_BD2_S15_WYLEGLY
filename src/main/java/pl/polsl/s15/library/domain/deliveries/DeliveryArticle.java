package pl.polsl.s15.library.domain.deliveries;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.stock.ArticleDetails;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "delivery_articles")
public class DeliveryArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;

    @Column(name = "delivery_request_date")
    private LocalDateTime deliveryRequestDate;

    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate;

    @ManyToOne
    @JoinColumn(name = "details_id")
    private ArticleDetails articleDetails;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}