package pl.polsl.s15.library.domain.stock;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "article_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ArticleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "articleDetails")
    private List<DeliveryArticle> articles;
}
