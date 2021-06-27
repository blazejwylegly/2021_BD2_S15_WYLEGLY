package pl.polsl.s15.library.service;

import org.springframework.stereotype.Service;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.ClientsDTOMapper;
import pl.polsl.s15.library.repository.ClientRepository;
import pl.polsl.s15.library.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientService extends UserService {

    private static final List<String> DEFAULT_CLIENT_ROLES = Arrays.asList("USER", "CLIENT");

    private ClientRepository clientRepository;

    public ClientService(UserRepository userRepository,
                         RoleService roleService,
                         ClientRepository clientRepository) {
        super(userRepository, roleService);
        this.clientRepository = clientRepository;
    }

    public void createClient(ClientDTO clientDTO) {
        validateIfUserExistsByCredentials(clientDTO);
        Client client = ClientsDTOMapper.clientToEntity(clientDTO);
        addDefaultRoles(DEFAULT_CLIENT_ROLES, client);
        createCartForNewUser(client);
        clientRepository.save(client);
    }

    private void createCartForNewUser(Client client) {
        Cart cart = new Cart();
        client.setCart(cart);
    }
}
