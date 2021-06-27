package pl.polsl.s15.library.dtos.users;

import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.dtos.ordering.CartDTO;
import pl.polsl.s15.library.dtos.ordering.CartDTOMapper;

public class ClientsDTOMapper extends UsersDTOMapper {

    public static Client clientToEntity(ClientDTO clientDTO) {
        User user = userToEntity(clientDTO);
        Client client = new Client(user);
        if(clientDTO.getCartDTO() != null) {
            Cart cart = CartDTOMapper.toEntity(clientDTO.getCartDTO());
            client.setCart(cart);
        }
        return client;
    }

    public static ClientDTO clientToDTO(Client client) {
        CartDTO cartDTO = CartDTOMapper.toDTO(client.getCart());
        UserDTO userDTO = userToDTO(client);
        ClientDTO clientDTO = new ClientDTO(userDTO);
        clientDTO.setCartDTO(cartDTO);
        return clientDTO;
    }
}
