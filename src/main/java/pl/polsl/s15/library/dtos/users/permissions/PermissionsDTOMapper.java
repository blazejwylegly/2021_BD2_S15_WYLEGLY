package pl.polsl.s15.library.dtos.users.permissions;

import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.domain.user.account.roles.Authority;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTOMapper;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTOMapper;

import java.util.Set;

public class PermissionsDTOMapper {
    public static AccountPermissions toEntity(AccountPermissionsDTO permissionsDTO) {
        Set<Authority> authorities = AuthorityDTOMapper.toEntity(permissionsDTO.getAuthorities());
        Set<Role> roles = RoleDTOMapper.toEntity(permissionsDTO.getRoles());

        return AccountPermissions.builder()
                .id(permissionsDTO.getId())
                .authorities(authorities)
                .roles(roles)
                .build();
    }

    public static AccountPermissionsDTO toDTO(AccountPermissions accountPermissions) {
        Set<AuthorityDTO> authorityDTOS =
                AuthorityDTOMapper.toDTO(accountPermissions.getAuthorities());
        Set<RoleDTO> rolesDTO =
                RoleDTOMapper.toDTO(accountPermissions.getRoles());

        return AccountPermissionsDTO.builder()
                .id(accountPermissions.getId())
                .authorities(authorityDTOS)
                .roles(rolesDTO)
                .build();
    }
}
