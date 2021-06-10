package pl.polsl.s15.library.service;

import org.springframework.stereotype.Service;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.ClientsDTOMapper;
import pl.polsl.s15.library.repository.ClientRepository;
import pl.polsl.s15.library.repository.UserRepository;

@Service
public class ClientService extends UserService {

    private ClientRepository clientRepository;

    public ClientService(UserRepository userRepository,
                         ClientRepository clientRepository) {
        super(userRepository);
        this.clientRepository = clientRepository;
    }

    public void createClient(ClientDTO clientDTO) {
        validateIfUserExists(clientDTO);
        clientRepository.save(ClientsDTOMapper.clientDTOtoEntity(clientDTO));
    }
}
