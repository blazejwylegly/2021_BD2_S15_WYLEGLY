package pl.polsl.s15.library.api.controller.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.api.request.ClientRegistrationRequestDTO;
import pl.polsl.s15.library.api.request.RegistrationRequestDTO;
import pl.polsl.s15.library.api.response.ResponseDTO;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.service.ClientService;
import pl.polsl.s15.library.service.UserService;

@RestController
@RequestMapping("/api/public")
public class RegistrationController {

    private RegistrationDTOMapper dtoMapper;
    private UserService userService;
    private ClientService clientService;

    public RegistrationController(RegistrationDTOMapper dtoMapper,
                                  UserService userService,
                                  ClientService clientService) {
        this.dtoMapper = dtoMapper;
        this.userService = userService;
        this.clientService = clientService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<ResponseDTO> registerNewUser(@RequestBody RegistrationRequestDTO requestDTO) {
        UserDTO userDTO = dtoMapper.mapRequestToUser(requestDTO);
        userService.createUser(userDTO);
        return ResponseEntity.ok()
                .body(dtoMapper.userRegistrationSuccessful());
    }

    @PostMapping("/register/client")
    public ResponseEntity<ResponseDTO> registerNewClient(@RequestBody ClientRegistrationRequestDTO requestDTO) {
        ClientDTO clientDTO = dtoMapper.mapRequestToClient(requestDTO);
        clientService.createClient(clientDTO);
        return ResponseEntity.ok()
                .body(dtoMapper.userRegistrationSuccessful());

    }
}
