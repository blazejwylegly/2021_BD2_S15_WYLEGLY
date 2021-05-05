package pl.polsl.s15.library.domain.deliveries;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "deliveries")
public class Delivery  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "delivery")
    private List<DeliveryArticle> orderedItems;
}
