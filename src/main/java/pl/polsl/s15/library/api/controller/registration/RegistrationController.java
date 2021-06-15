package pl.polsl.s15.library.api.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.api.controller.registration.request.ClientRegistrationRequestDTO;
import pl.polsl.s15.library.api.controller.registration.request.EmployeeRegistrationRequestDTO;
import pl.polsl.s15.library.api.controller.registration.request.RegistrationRequestDTO;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.EmployeeDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.service.ClientService;
import pl.polsl.s15.library.service.EmployeeService;
import pl.polsl.s15.library.service.UserService;

@RestController
@RequestMapping("/api/public")
public class RegistrationController {

    private RegistrationReqRepMapper dtoMapper;
    private UserService userService;
    private ClientService clientService;
    private EmployeeService employeeService;

    @Autowired
    public RegistrationController(RegistrationReqRepMapper dtoMapper,
                                  UserService userService,
                                  ClientService clientService,
                                  EmployeeService employeeService) {
        this.dtoMapper = dtoMapper;
        this.userService = userService;
        this.clientService = clientService;
        this.employeeService = employeeService;
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

    @PostMapping("/register/employee")
    public ResponseEntity<ResponseDTO> registerNewEmployee(@RequestBody EmployeeRegistrationRequestDTO requestDTO) {
        EmployeeDTO employeeDTO = dtoMapper.mapRequestToEmployee(requestDTO);
        employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok()
                .body(dtoMapper.userRegistrationSuccessful());
    }

}
