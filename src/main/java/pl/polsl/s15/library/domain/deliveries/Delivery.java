package pl.polsl.s15.library.domain.deliveries;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "deliveries")
public class Delivery  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "delivery")
    private List<DeliveryArticle> orderedItems;
}
