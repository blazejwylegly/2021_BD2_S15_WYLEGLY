package pl.polsl.s15.library.domain.user;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.reservations.Reservation;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends User {
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;
}
