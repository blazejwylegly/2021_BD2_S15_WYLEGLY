package pl.polsl.s15.library.controller.registration.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.AccountCredentials;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;

import java.util.Date;

@Component
public class RegistrationDTOMapper {

    private static final String REGISTRATION_SUCCESS_MSG = "User registration successful";
    private static final String REGISTRATION_FAIL_MSG = "User registration failed";

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

    public RegistrationResponseDTO userRegistrationSuccessful() {
        return RegistrationResponseDTO.builder()
                .message(REGISTRATION_SUCCESS_MSG)
                .build();
    }

    public RegistrationResponseDTO userRegistrationFailed(RegistrationRequestDTO request) {
        return RegistrationResponseDTO.builder()
                .message(REGISTRATION_FAIL_MSG)
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
