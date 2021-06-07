package pl.polsl.s15.library.dtos;

import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.domain.user.account.AccountCredentials;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.dtos.ordering.CartDTO;
import pl.polsl.s15.library.dtos.ordering.OrderItemDTO;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

public class DTOMapper {

    public static ClientDTO clientEntityToDTO(Client client) {

    }

    public static Client clientDTOtoEntity(ClientDTO clientDTO) {
        AccountPermissions permissions = permissionsDTOtoEntity(clientDTO.getAccountPermissionsDTO());
        AccountCredentials credentials = credentialsDTOtoEntity(clientDTO.getAccountCredentialsDTO());
        Cart cart = Cart.ofDTO(clientDTO.getCartDTO());
        Client client = Client.clientBuilder()
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .photoUrl(clientDTO.getPhotoUrl())
                .build();
    }

    private static AccountCredentials credentialsDTOtoEntity(AccountCredentialsDTO accountCredentialsDTO) {



    }

    private static AccountPermissions permissionsDTOtoEntity(AccountPermissionsDTO accountPermissionsDTO) {
        return null;
    }

    public static CartDTO cartEntityToDTO(Cart cart) {
        CartDTO cartDTO = CartDTO.builder()
                .id(cart.getId())
                .build();
    }

    public static Cart cartDTOtoEntity(CartDTO cartDTO) {

    }

    public static OrderItemDTO orderItemEntityToDTO(OrderItem orderItem) {

    }

}
