package pl.polsl.s15.library.domain.stock;

import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ArticleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "articleDetails")
    private List<DeliveryArticle> articles;
}
