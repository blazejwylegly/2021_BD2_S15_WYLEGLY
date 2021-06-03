package pl.polsl.s15.library.dtos.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.commons.exceptions.authentication.UserAlreadyRegisteredException;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.AccountCredentials;
import pl.polsl.s15.library.dtos.common.api.ErrorResponseDTO;
import pl.polsl.s15.library.dtos.common.api.ResponseDTO;

import java.util.Date;

@Component
public class RegistrationDTOMapper {

    private static final String REGISTRATION_SUCCESS_MSG = "User registration successful";

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationDTOMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapRegistrationRequestToUser(RegistrationRequestDTO request) {
        AccountCredentials credentials = accountCredentials(request);
        return User.builder()
                .credentials(credentials)
                .build();
    }

    public ResponseDTO userRegistrationSuccessful() {
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message(REGISTRATION_SUCCESS_MSG)
                .timestamp(new Date())
                .build();
    }

    private AccountCredentials accountCredentials(RegistrationRequestDTO request) {
        return AccountCredentials.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailAddress(request.getEmail())
                .build();
    }
}
