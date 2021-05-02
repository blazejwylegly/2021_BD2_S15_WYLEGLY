package pl.polsl.s15.library.domain.user;

import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.reservations.Reservation;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="clients")
public class Client extends User {
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;
}
