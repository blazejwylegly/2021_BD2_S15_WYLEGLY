package pl.polsl.s15.library.dtos.users.permissions.roles;

import pl.polsl.s15.library.domain.user.account.roles.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleDTOMapper {
    public static Set<Role> toEntity(Set<RoleDTO> roles) {
        return roles.stream()
                .map(RoleDTOMapper::toEntity)
                .collect(Collectors.toSet());
    }

    public static Role toEntity(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .roleType(roleDTO.getRoleName())
                .build();
    }

    public static Set<RoleDTO> toDTO(Set<Role> roles) {
        return roles.stream()
                .map(RoleDTOMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public static RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .roleName(role.getRoleType())
                .build();
    }

}
