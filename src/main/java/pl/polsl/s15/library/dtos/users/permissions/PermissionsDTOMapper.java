package pl.polsl.s15.library.dtos.users.permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.domain.user.account.roles.Authority;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTOMapper;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTOMapper;

import java.util.HashSet;
import java.util.Set;

@Component
public class PermissionsDTOMapper {

    private RoleDTOMapper roleDTOMapper;

    @Autowired
    public PermissionsDTOMapper(RoleDTOMapper roleDTOMapper) {
        this.roleDTOMapper = roleDTOMapper;
    }

    public AccountPermissions toEntity(AccountPermissionsDTO permissionsDTO) {
        Set<Authority> authorities = AuthorityDTOMapper.toEntity(permissionsDTO.getAuthorities());
        Set<Role> roles = roleDTOMapper.toEntity(permissionsDTO.getRoles());

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
