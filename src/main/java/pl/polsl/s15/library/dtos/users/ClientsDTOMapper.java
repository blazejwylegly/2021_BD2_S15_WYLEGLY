package pl.polsl.s15.library.dtos.users;

import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.domain.user.User;

public class ClientsDTOMapper extends UsersDTOMapper {

    public static Client clientDTOtoEntity(ClientDTO clientDTO) {
        User user = userDTOtoEntity(clientDTO);
        return new Client(user);
    }
}
