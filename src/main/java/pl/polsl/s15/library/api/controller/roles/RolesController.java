package pl.polsl.s15.library.api.controller.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.api.controller.base.BaseController;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.api.controller.roles.response.GetRolesResponseDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;
import pl.polsl.s15.library.service.RoleService;

import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RolesController extends BaseController {

    private RoleService roleService;

    @Autowired
    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetRolesResponseDTO> getUserRoles(@PathVariable("userId") long userId) {
        Set<RoleDTO> roles = roleService.getUserRoles(userId);
        return ResponseEntity.ok()
                .body(RoleReqRepMapper.getRolesResponse(roles));
    }

    @GetMapping("/available")
    public ResponseEntity<GetRolesResponseDTO> getAllAvailableRoles() {
        Set<RoleDTO> roles = roleService.getAll();
        return ResponseEntity.ok()
                .body(RoleReqRepMapper.getRolesResponse(roles));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ResponseDTO> deleteRoleById(@PathVariable("roleId") long roleId) {
        roleService.deleteRoleById(roleId);
        return ResponseEntity.ok()
                .body(RoleReqRepMapper.roleByIdDeleted(roleId));
    }

    @DeleteMapping("/{roleName}")
    public ResponseEntity<ResponseDTO> deleteRoleByName(@PathVariable("roleName") String roleName) {
        roleService.deleteRoleByName(roleName);
        return ResponseEntity.ok()
                .body(RoleReqRepMapper.roleByNameDeleted(roleName));
    }
}
