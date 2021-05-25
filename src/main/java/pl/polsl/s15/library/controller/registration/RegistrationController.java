package pl.polsl.s15.library.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.controller.registration.dto.RegistrationDtoMapper;
import pl.polsl.s15.library.controller.registration.dto.RegistrationRequest;
import pl.polsl.s15.library.controller.registration.dto.RegistrationResponse;
import pl.polsl.s15.library.controller.registration.exception.UserAlreadyRegisteredException;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public")
public class RegistrationController {

    private RegistrationDtoMapper dtoMapper;
    private UserService userService;

    @Autowired
    public RegistrationController(RegistrationDtoMapper dtoMapper, UserService userService) {
        this.dtoMapper = dtoMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerNewUser(@RequestBody RegistrationRequest request) {
        User user = dtoMapper.mapRegistrationRequestToUser(request);
        try {
            userService.createUser(user);
            return ResponseEntity.ok()
                    .body(dtoMapper.successfulUserRegistration(user));
        } catch (UserAlreadyRegisteredException ex) {
            return ResponseEntity.badRequest()
                    .body(dtoMapper.userRegistrationFailed(request, ex.getMessage()));
        }
    }
}
