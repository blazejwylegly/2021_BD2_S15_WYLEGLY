package pl.polsl.s15.library.api.controller.roles;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.api.controller.roles.request.RoleCreationRequestDTO;
import pl.polsl.s15.library.api.controller.roles.response.GetRolesResponseDTO;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleReqRepMapper {
    public static GetRolesResponseDTO getRolesResponse(Set<RoleDTO> userRoles) {
        return GetRolesResponseDTO.getRolesResponseBuilder()
                .status(HttpStatus.OK)
                .message("Roles received successfully")
                .timestamp(new Date())
                .roles(mapRolesToPlainText(userRoles))
                .build();
    }

    private static Set<RoleType> mapRolesToPlainText(Set<RoleDTO> userRoles) {
        return userRoles.stream()
                .map(RoleDTO::getRoleName)
                .collect(Collectors.toSet());
    }

    private static ResponseDTO roleDeleted(String msg) {
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message(msg)
                .timestamp(new Date())
                .build();
    }

    public static ResponseDTO roleByIdDeleted(long roleId) {
        return roleDeleted(String.format("Role with id %s deleted successfully", roleId));
    }

    public static ResponseDTO roleByNameDeleted(String roleName) {
        return roleDeleted(String.format("Role %s deleted successfully", roleName));
    }

//    public static RoleDTO mapCreateRequestToRole(RoleCreationRequestDTO requestDTO) {
//        return RoleDTO.builder()
//                .role(requestDTO.getRoleName())
//                .build()
//    }
}
