package pl.polsl.s15.library.domain.orderable;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.cart.Cart;
import pl.polsl.s15.library.domain.orderItem.OrderItem;
import pl.polsl.s15.library.domain.orderablePhoto.OrderablePhoto;
import pl.polsl.s15.library.domain.reservation.Reservation;
import pl.polsl.s15.library.domain.strategy.IdStrategyClass;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "orderables")
public class Orderable extends IdStrategyClass {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    private BigInteger duration;

    private Long amount;

    private Long markedForOrderAmount;

    private Integer isOccupied;

    private Long serialNumber;

    private Integer isMarkedForReservation;

    @OneToMany(mappedBy = "orderable")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "orderable")
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToMany(mappedBy = "cartOrderables")
    private List<Cart> carts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "photos_orderables",
            joinColumns = @JoinColumn(name = "orderable_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_url"))
    private List<OrderablePhoto> orderablePhotos = new ArrayList<>();
}
