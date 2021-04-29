package pl.polsl.s15.library.domain.orderablePhoto;


import pl.polsl.s15.library.domain.orderable.Orderable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orderable_photos")
public class OrderablePhoto {

    @Id
    @Column(nullable = false)
    private String url;

    @ManyToMany(mappedBy = "orderablePhotos")
    private List<Orderable> orderables = new ArrayList<>();

}
