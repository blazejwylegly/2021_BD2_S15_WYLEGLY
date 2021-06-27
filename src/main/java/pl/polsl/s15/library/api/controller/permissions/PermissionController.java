package pl.polsl.s15.library.api.controller.permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.api.controller.base.response.permissions.AuthoritiesResponseDTO;
import pl.polsl.s15.library.api.controller.base.response.permissions.PermissionsResponseDTO;
import pl.polsl.s15.library.commons.exceptions.user.UserNotFoundException;
import pl.polsl.s15.library.service.UserService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private UserService userService;

    @Autowired
    public PermissionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PermissionsResponseDTO> getUserPermissions(@PathVariable("userId") long userId) {
        PermissionsResponseDTO response = userService.getPermissionsForUser(userId)
                .map(PermissionReqRepMapper::permissionsResponse)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found!"));
        return ResponseEntity.ok(response);
    }


    @GetMapping("/authorities/{userId}")
    public ResponseEntity<AuthoritiesResponseDTO> getUserAuthorities(@PathVariable("userId") long userId) {
        return null;
    }

}
