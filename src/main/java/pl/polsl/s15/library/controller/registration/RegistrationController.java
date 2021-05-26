package pl.polsl.s15.library.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.controller.registration.dto.RegistrationDTOMapper;
import pl.polsl.s15.library.controller.registration.dto.RegistrationRequestDTO;
import pl.polsl.s15.library.commons.exceptions.UserAlreadyRegisteredException;
import pl.polsl.s15.library.controller.registration.dto.RegistrationResponseDTO;
import pl.polsl.s15.library.domain.user.User;
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
    public ResponseEntity<RegistrationResponseDTO> registerNewUser(@RequestBody RegistrationRequestDTO request) {
        User user = dtoMapper.mapRegistrationRequestToUser(request);
        try {
            userService.createUser(user);
            return ResponseEntity.ok()
                    .body(dtoMapper.userRegistrationSuccessful());
        } catch (UserAlreadyRegisteredException ex) {
            return ResponseEntity.badRequest()
                    .body(dtoMapper.userRegistrationFailed(request));
        }
    }
}
