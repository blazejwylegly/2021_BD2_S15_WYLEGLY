package pl.polsl.s15.library.api.controller.permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.api.response.permissions.AuthoritiesResponseDTO;
import pl.polsl.s15.library.api.response.permissions.PermissionsResponseDTO;
import pl.polsl.s15.library.api.response.permissions.RolesResponseDTO;
import pl.polsl.s15.library.commons.exceptions.user.UserNotFoundException;
import pl.polsl.s15.library.service.UserService;

@RestController
@RequestMapping("/api")
public class PermissionController {

    private UserService userService;

    @Autowired
    public PermissionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/permissions/{userId}")
    public ResponseEntity<PermissionsResponseDTO> getUserPermissions(@PathVariable("userId") long userId) {
        PermissionsResponseDTO response = userService.getPermissionsForUser(userId)
                .map(PermissionDTOMapper::permissionsResponse)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found!"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/roles/{userId}")
    public ResponseEntity<RolesResponseDTO> getUserRoles(@PathVariable("userId") long userId) {
        return null;
    }

    @GetMapping("/authorities/{userId}")
    public ResponseEntity<AuthoritiesResponseDTO> getUserAuthorities(@PathVariable("userId") long userId) {
        return null;
    }

    @PostMapping("/roles/new")
    public ResponseEntity<ResponseDTO> createNewRole() {
        return null;
    }

    @PostMapping("authorities/new")
    public ResponseEntity<ResponseDTO> createNewAuthority() {
        return null;
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<ResponseDTO> deleteRoleWithId() {
        return null;
    }

    @DeleteMapping("/authorities/{id}")
    public ResponseEntity<ResponseDTO> deleteAuthorityWithId() {
        return null;
    }


}
