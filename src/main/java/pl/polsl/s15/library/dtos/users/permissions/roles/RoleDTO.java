package pl.polsl.s15.library.dtos.users.permissions.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;

@Getter
@Builder
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private RoleType roleName;

    private RoleDTO(RoleType roleName) {
        this.roleName = roleName;
    }

    public static RoleDTO of(String roleName) {
        return new RoleDTO(RoleType.valueOf(roleName));
    }
}
