package pl.polsl.s15.library.dtos.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.AccountCredentials;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.credentials.CredentialsDTOMapper;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.PermissionsDTOMapper;

@Component
public class UsersDTOMapper {

    private PermissionsDTOMapper permissionsDTOMapper;

    @Autowired
    public UsersDTOMapper(PermissionsDTOMapper permissionsDTOMapper) {
        this.permissionsDTOMapper = permissionsDTOMapper;
    }

    public User userToEntity(UserDTO userDTO) {
        AccountPermissions permissions =
                permissionsDTOMapper.toEntity(userDTO.getAccountPermissionsDTO());
        AccountCredentials credentials =
                CredentialsDTOMapper.toEntity(userDTO.getAccountCredentialsDTO());

        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .photoUrl(userDTO.getPhotoUrl())
                .credentials(credentials)
                .permissions(permissions)
                .build();
    }

    public static UserDTO userToDTO(User user) {
        AccountPermissionsDTO permissions =
                PermissionsDTOMapper.toDTO(user.getPermissions());
        AccountCredentialsDTO credentials =
                CredentialsDTOMapper.toDTO(user.getCredentials());

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .photoUrl(user.getPhotoUrl())
                .accountCredentialsDTO(credentials)
                .accountPermissionsDTO(permissions)
                .build();
    }
}
