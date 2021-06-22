package pl.polsl.s15.library.api.controller.user.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.api.controller.user.request.ClientCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.api.controller.user.request.EmployeeCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.api.controller.user.request.UserCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.EmployeeDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.service.ClientService;
import pl.polsl.s15.library.service.EmployeeService;
import pl.polsl.s15.library.service.UserService;

@RestController
@RequestMapping("/api/public")
public class RegistrationController {

    private RegistrationReqRepMapper reqRepMapper;
    private UserService userService;
    private ClientService clientService;
    private EmployeeService employeeService;

    @Autowired
    public RegistrationController(RegistrationReqRepMapper reqRepMapper,
                                  UserService userService,
                                  ClientService clientService,
                                  EmployeeService employeeService) {
        this.reqRepMapper = reqRepMapper;
        this.userService = userService;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<ResponseDTO> registerNewUser(@RequestBody UserCreateOrUpdateRequestDTO requestDTO) {
        UserDTO userDTO = reqRepMapper.mapRequestToUser(requestDTO);
        userService.createUser(userDTO);
        return ResponseEntity.ok()
                .body(reqRepMapper.userRegistrationSuccessful());
    }

    @PostMapping("/register/client")
    public ResponseEntity<ResponseDTO> registerNewClient(@RequestBody ClientCreateOrUpdateRequestDTO requestDTO) {
        ClientDTO clientDTO = reqRepMapper.mapRequestToClient(requestDTO);
        clientService.createClient(clientDTO);
        return ResponseEntity.ok()
                .body(reqRepMapper.userRegistrationSuccessful());
    }

    @PostMapping("/register/employee")
    public ResponseEntity<ResponseDTO> registerNewEmployee(@RequestBody EmployeeCreateOrUpdateRequestDTO requestDTO) {
        EmployeeDTO employeeDTO = reqRepMapper.mapRequestToEmployee(requestDTO);
        employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok()
                .body(reqRepMapper.userRegistrationSuccessful());
    }

}
