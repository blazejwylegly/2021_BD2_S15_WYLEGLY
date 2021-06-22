package pl.polsl.s15.library.api.controller.user.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.api.controller.user.UserReqRepMapper;
import pl.polsl.s15.library.api.controller.user.request.ClientCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.api.controller.user.request.EmployeeCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.api.controller.user.request.UserCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.EmployeeDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Qualifier("registrationReqRepMapper")
public class RegistrationReqRepMapper extends UserReqRepMapper {

    private static final String REGISTRATION_SUCCESS_MSG = "Registration successful";

    @Autowired
    public RegistrationReqRepMapper(PasswordEncoder passwordEncoder) {
        super(passwordEncoder);
    }

    public ResponseDTO userRegistrationSuccessful() {
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message(REGISTRATION_SUCCESS_MSG)
                .timestamp(new Date())
                .build();
    }
}
