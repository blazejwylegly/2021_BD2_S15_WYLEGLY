package pl.polsl.s15.library.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.api.controller.user.request.ClientCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.api.controller.user.request.EmployeeCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.api.controller.user.request.UserCreateOrUpdateRequestDTO;
import pl.polsl.s15.library.api.controller.user.response.GetAccountMetaDataResponse;
import pl.polsl.s15.library.api.controller.user.response.GetAllUsersResponse;
import pl.polsl.s15.library.api.controller.user.response.GetUserResponse;
import pl.polsl.s15.library.dtos.users.ClientDTO;
import pl.polsl.s15.library.dtos.users.EmployeeDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.meta.AccountMetaData;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserReqRepMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserReqRepMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public GetAllUsersResponse getAllUsersResponse(List<UserDTO> users) {
        return GetAllUsersResponse.getUsersResponseBuilder()
                .status(HttpStatus.OK)
                .message("Successfully retrieved all users")
                .timestamp(new Date())
                .users(users)
                .build();
    }

    public GetAccountMetaDataResponse getAccountMetaDataResponse(AccountMetaData accountMetaData) {
        return GetAccountMetaDataResponse.getAccountMetaDataResponseBuilder()
                .status(HttpStatus.OK)
                .message("Successfully retrieved account meta data")
                .timestamp(new Date())
                .userId(accountMetaData.getUserId())
                .permissions(accountMetaData.getPermissionsDTO())
                .build();
    }


    public UserDTO mapRequestToUser(UserCreateOrUpdateRequestDTO request) {
        AccountCredentialsDTO accountCredentialsDTO = accountCredentials(request);
        AccountPermissionsDTO accountPermissionsDTO = accountPermissions(request);
        return UserDTO.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .photoUrl(request.getPhotoUrl())
                .accountCredentialsDTO(accountCredentialsDTO)
                .accountPermissionsDTO(accountPermissionsDTO)
                .build();
    }

    private AccountPermissionsDTO accountPermissions(UserCreateOrUpdateRequestDTO request) {
        Set<AuthorityDTO> authorities = getAuthoritiesFromRequest(request);
        Set<RoleDTO> roles = getRolesFromRequest(request);
        return AccountPermissionsDTO.builder()
                .authorities(authorities)
                .roles(roles)
                .build();
    }

    private Set<RoleDTO> getRolesFromRequest(UserCreateOrUpdateRequestDTO request) {
        return request.getRoles()
                .stream()
                .map(RoleDTO::of)
                .collect(Collectors.toSet());
    }

    private Set<AuthorityDTO> getAuthoritiesFromRequest(UserCreateOrUpdateRequestDTO request) {
        return request.getAuthorities()
                .stream()
                .map(AuthorityDTO::of)
                .collect(Collectors.toSet());
    }

    private AccountCredentialsDTO accountCredentials(UserCreateOrUpdateRequestDTO request) {
        return AccountCredentialsDTO.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailAddress(request.getEmail())
                .build();
    }

    public ClientDTO mapRequestToClient(ClientCreateOrUpdateRequestDTO request) {
        UserDTO userDTO = mapRequestToUser(request);
        return new ClientDTO(userDTO);
    }

    public EmployeeDTO mapRequestToEmployee(EmployeeCreateOrUpdateRequestDTO requestDTO) {
        UserDTO employeeDTO = mapRequestToUser(requestDTO);
        return new EmployeeDTO(employeeDTO);
    }

    public ResponseDTO userUpdateSuccessfulResponse() {
        return ResponseDTO.builder()
                .status(HttpStatus.NO_CONTENT)
                .message("User update executed successfully")
                .timestamp(new Date())
                .build();
    }

    public ResponseDTO userRoleAddedSuccessfullyResponse() {
        return ResponseDTO.builder()
                .status(HttpStatus.NO_CONTENT)
                .message("User has been updated with new role!")
                .timestamp(new Date())
                .build();
    }

    public ResponseDTO userRoleDeletedSuccessfullyResponse() {
        return ResponseDTO.builder()
                .status(HttpStatus.NO_CONTENT)
                .message("User role has been removed!")
                .timestamp(new Date())
                .build();
    }

    public ResponseDTO userDeleteSuccessfullyResponse() {
        return ResponseDTO.builder()
                .status(HttpStatus.NO_CONTENT)
                .message("User has been removed!")
                .timestamp(new Date())
                .build();
    }

    public GetUserResponse getSingleUserResponse(UserDTO userDTO) {
        return GetUserResponse.getUserResponseBuilder()
                .status(HttpStatus.OK)
                .message("User retrieved successfully!")
                .timestamp(new Date())
                .userDTO(userDTO)
                .build();
    }
}
