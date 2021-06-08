package pl.polsl.s15.library.dtos.users;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

@Getter
@Builder
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private AccountCredentialsDTO accountCredentialsDTO;
    private AccountPermissionsDTO accountPermissionsDTO;
}
