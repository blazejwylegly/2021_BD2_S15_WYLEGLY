package pl.polsl.s15.library.dtos.users;

import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.AccountCredentials;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.dtos.users.credentials.CredentialsDTOMapper;
import pl.polsl.s15.library.dtos.users.permissions.PermissionsDTOMapper;

public class UsersDTOMapper {
    public static User userDTOtoEntity(UserDTO userDTO) {

        AccountPermissions permissions =
                PermissionsDTOMapper.permissionsDTOtoEntity(userDTO.getAccountPermissionsDTO());
        AccountCredentials credentials =
                CredentialsDTOMapper.credentialsDTOtoEntity(userDTO.getAccountCredentialsDTO());

        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .photoUrl(userDTO.getPhotoUrl())
                .credentials(credentials)
                .permissions(permissions)
                .build();
    }

}
