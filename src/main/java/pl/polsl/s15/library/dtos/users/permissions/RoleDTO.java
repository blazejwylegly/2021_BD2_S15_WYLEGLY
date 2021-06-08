package pl.polsl.s15.library.dtos.users.permissions;

import lombok.*;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;

@Getter
@Builder
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private RoleType roleType;

    private RoleDTO(RoleType roleType) {
        this.roleType = roleType;
    }

    public static RoleDTO of(String roleType) {
        return RoleDTO.builder()
                .roleType(RoleType.valueOf(roleType))
                .build();
    }

    public static RoleDTO of(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .roleType(role.getName())
                .build();
    }
}
