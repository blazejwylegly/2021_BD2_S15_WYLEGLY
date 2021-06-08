package pl.polsl.s15.library.domain.stock;

import lombok.Getter;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ArticleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToMany(mappedBy = "articleDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<DeliveryArticle> articles;
}
