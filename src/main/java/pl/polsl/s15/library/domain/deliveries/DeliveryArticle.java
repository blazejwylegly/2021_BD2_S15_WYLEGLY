package pl.polsl.s15.library.domain.deliveries;

import pl.polsl.s15.library.domain.stock.ArticleDetails;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_articles")
public class DeliveryArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;

    private LocalDateTime deliveryRequestDate;

    private LocalDateTime expectedDeliveryDate;

    @ManyToOne
    @JoinColumn(name = "details_id")
    private ArticleDetails articleDetails;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}
