package pl.polsl.s15.library.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.reservations.Reservation;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.AccountCredentials;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client extends User {
    @OneToOne
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

    public static Client of(ClientDTO clientDTO) {
        AccountPermissions permissions = AccountPermissions.of(clientDTO.getAccountPermissionsDTO());
        AccountCredentials credentials = AccountCredentials.of(clientDTO.getAccountCredentialsDTO());
        Cart cart = Cart.ofDTO(clientDTO.getCartDTO());
        return Client.clientBuilder()
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .photoUrl(clientDTO.getPhotoUrl())
                .credentials(credentials)
                .permissions(permissions)
                .cart()
                .build();
    }
}
