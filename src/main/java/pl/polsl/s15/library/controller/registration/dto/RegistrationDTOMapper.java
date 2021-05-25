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

    private static final String REGISTRATION_SUCCESS_MSG = "Successfully registered new user";

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationDTOMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapRegistrationRequestToUser(RegistrationRequestDTO request) {
        AccountCredentials credentials = accountCredentials(request);
        AccountPermissions permissions = new AccountPermissions();
        return User.builder()
                .credentials(credentials)
                //.permissions(permissions)
                .build();
    }

    public RegistrationResponseDTO successfulUserRegistration(User user) {
        return RegistrationSuccessfulResponseDTO.builder()
                .message(REGISTRATION_SUCCESS_MSG)
                .username(user.getUsername())
                .emailAddress(user.getCredentials().getEmailAddress())
                .build();
    }

    public RegistrationResponseDTO userRegistrationFailed(RegistrationRequestDTO request, String causeMessage) {
        return new RegistrationResponseDTO(causeMessage, new Date());
    }

    private AccountCredentials accountCredentials(RegistrationRequestDTO request) {
        return AccountCredentials.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailAddress(request.getEmail())
                .build();
    }
}
