package pl.polsl.s15.library.api.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.api.request.RegistrationRequestDTO;
import pl.polsl.s15.library.api.response.ResponseDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.service.UserService;

@RestController
@RequestMapping("/api/public")
public class RegistrationController {

    private RegistrationDTOMapper dtoMapper;
    private UserService userService;

    @Autowired
    public RegistrationController(RegistrationDTOMapper dtoMapper, UserService userService) {
        this.dtoMapper = dtoMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerNewUser(@RequestBody RegistrationRequestDTO requestDTO) {
        UserDTO userDTO = dtoMapper.mapRequestToUser(requestDTO);
        userService.createUser(userDTO);
        return ResponseEntity.ok()
                .body(dtoMapper.userRegistrationSuccessful());
    }
}
