package pl.polsl.s15.library.dtos.users;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;

@Getter
@Builder
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private AccountCredentialsDTO accountCredentialsDTO;
    private AccountPermissionsDTO accountPermissionsDTO;

    public void addNewRole(String roleName) {
        this.accountPermissionsDTO.addNewRoleIfNotPresent(RoleDTO.of(roleName));
    }
}
