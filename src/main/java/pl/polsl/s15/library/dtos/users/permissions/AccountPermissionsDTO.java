package pl.polsl.s15.library.dtos.users.permissions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;

import java.util.Set;

@Builder
@Getter
@Data
public class AccountPermissionsDTO {

    private Long id;
    private Set<AuthorityDTO> authorities;
    private Set<RoleDTO> roles;

    public void addNewRoleIfNotPresent(RoleDTO roleDTO) {
        if(!roleExists(roleDTO)) {
            this.roles.add(roleDTO);
        }
    }

    private boolean roleExists(RoleDTO roleDTo) {
        return roles.stream()
                .anyMatch(existingRole -> existingRole.getRoleName().equals(roleDTo.getRoleName()));
    }
}
