package pl.polsl.s15.library.dtos.users.permissions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.domain.user.account.roles.Authority;
import pl.polsl.s15.library.domain.user.account.roles.Role;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Data
public class AccountPermissionsDTO {

    private Long id;
    private Set<AuthorityDTO> authorities;
    private Set<RoleDTO> roles;

    public static AccountPermissionsDTO from(AccountPermissions permissions) {
        Set<AuthorityDTO> authorityDTOS = mapAuthoritiesToDTO(permissions.getAuthorities());
        Set<RoleDTO> roleDTOS = mapRolesToDTO(permissions.getRoles());

        return AccountPermissionsDTO.builder()
                .authorities(authorityDTOS)
                .roles(roleDTOS)
                .build();
    }

    public static Set<AuthorityDTO> mapAuthoritiesToDTO(Set<Authority> authorities) {
        return authorities.stream()
                .map(AuthorityDTO::of)
                .collect(Collectors.toSet());
    }

    public static Set<RoleDTO> mapRolesToDTO(Set<Role> roles) {
        return roles.stream()
                .map(RoleDTO::of)
                .collect(Collectors.toSet());
    }
}
