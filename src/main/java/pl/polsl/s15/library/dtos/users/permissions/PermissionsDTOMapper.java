package pl.polsl.s15.library.dtos.users.permissions;

import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.domain.user.account.roles.Authority;
import pl.polsl.s15.library.domain.user.account.roles.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class PermissionsDTOMapper {
    public static AccountPermissions permissionsDTOtoEntity(AccountPermissionsDTO permissionsDTO) {
        Set<Authority> authorities = authoritiesDTOtoEntities(permissionsDTO.getAuthorities());
        Set<Role> roles = rolesDTOtoEntities(permissionsDTO.getRoles());

        return AccountPermissions.builder()
                .id(permissionsDTO.getId())
                .authorities(authorities)
                .roles(roles)
                .build();
    }

    private static Set<Role> rolesDTOtoEntities(Set<RoleDTO> roles) {
        return roles.stream()
                .map(PermissionsDTOMapper::roleDTOtoEntity)
                .collect(Collectors.toSet());
    }

    private static Role roleDTOtoEntity(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getRoleType())
                .build();
    }

    public static Set<Authority> authoritiesDTOtoEntities(Set<AuthorityDTO> authorities) {
        return authorities.stream()
                .map(PermissionsDTOMapper::authorityDTOtoEntity)
                .collect(Collectors.toSet());
    }

    public static Authority authorityDTOtoEntity(AuthorityDTO authorityDTO) {
        return Authority.builder()
                .id(authorityDTO.getId())
                .authority(authorityDTO.getAuthority())
                .build();
    }
}
