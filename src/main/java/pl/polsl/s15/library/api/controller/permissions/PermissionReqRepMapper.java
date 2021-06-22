package pl.polsl.s15.library.api.controller.permissions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.api.controller.base.response.permissions.PermissionsResponseDTO;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissionReqRepMapper {

    public static PermissionsResponseDTO permissionsResponse(AccountPermissionsDTO permissions) {
        Set<String> plainAuthorities = getPlainAuthorities(permissions.getAuthorities());
        Set<String> plainRoles = getPlainRoles(permissions.getRoles());

        return PermissionsResponseDTO.permissionsResponseBuilder()
                .status(HttpStatus.OK)
                .message("Successfully retrieved permissions")
                .timestamp(new Date())
                .authorities(plainAuthorities)
                .roles(plainRoles)
                .build();
    }

    public static Set<String> getPlainAuthorities(Set<AuthorityDTO> authorityDTOS) {
        return authorityDTOS.stream()
                .map(AuthorityDTO::getName)
                .collect(Collectors.toSet());
    }

    public static Set<String> getPlainRoles(Set<RoleDTO> roleDTOS) {
        return roleDTOS.stream()
                .map(RoleDTO::getRoleName)
                .map(RoleType::getValue)
                .collect(Collectors.toSet());
    }
}
