package pl.polsl.s15.library.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.reservations.Reservation;
import pl.polsl.s15.library.domain.user.account.AccountCredentials;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client extends User {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;

    @Builder(builderMethodName = "clientBuilder")
    public Client(Long id,
                  String firstName,
                  String lastName,
                  String photoUrl,
                  AccountCredentials credentials,
                  AccountPermissions permissions,
                  Cart cart,
                  List<Reservation> reservations) {
        super(id, firstName, lastName, photoUrl, credentials, permissions);
        this.cart = cart;
        this.reservations = reservations;
    }
    public Client(User user)
    {
        super(user);
        reservations = new ArrayList<>();
    }
}
