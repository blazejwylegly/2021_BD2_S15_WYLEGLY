package pl.polsl.s15.library.api.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.api.controller.registration.request.ClientRegistrationRequestDTO;
import pl.polsl.s15.library.api.controller.registration.request.RegistrationRequestDTO;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.api.controller.roles.RoleReqRepMapper;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTOMapper;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RegistrationReqRepMapper {

    private static final String REGISTRATION_SUCCESS_MSG = "Registration successful";

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationReqRepMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseDTO userRegistrationSuccessful() {
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message(REGISTRATION_SUCCESS_MSG)
                .timestamp(new Date())
                .build();
    }

    public UserDTO mapRequestToUser(RegistrationRequestDTO request) {
        AccountCredentialsDTO accountCredentialsDTO = accountCredentials(request);
        AccountPermissionsDTO accountPermissionsDTO = accountPermissions(request);
        return UserDTO.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .photoUrl(request.getPhotoUrl())
                .accountCredentialsDTO(accountCredentialsDTO)
                .accountPermissionsDTO(accountPermissionsDTO)
                .build();
    }

    private AccountPermissionsDTO accountPermissions(RegistrationRequestDTO request) {
        Set<AuthorityDTO> authorities = getAuthoritiesFromRequest(request);
        Set<RoleDTO> roles = getRolesFromRequest(request);
        return AccountPermissionsDTO.builder()
                .authorities(authorities)
                .roles(roles)
                .build();
    }

    private Set<RoleDTO> getRolesFromRequest(RegistrationRequestDTO request) {
        return request.getRoles()
                .stream()
                .map(RoleDTO::of)
                .collect(Collectors.toSet());
    }

    private Set<AuthorityDTO> getAuthoritiesFromRequest(RegistrationRequestDTO request) {
        return request.getAuthorities()
                .stream()
                .map(AuthorityDTO::of)
                .collect(Collectors.toSet());
    }

    private AccountCredentialsDTO accountCredentials(RegistrationRequestDTO request) {
        return AccountCredentialsDTO.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailAddress(request.getEmail())
                .build();
    }

    public ClientDTO mapRequestToClient(ClientRegistrationRequestDTO request) {
        UserDTO userDTO = mapRequestToUser(request);
        return new ClientDTO(userDTO);
    }
}
