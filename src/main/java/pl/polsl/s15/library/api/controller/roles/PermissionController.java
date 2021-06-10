package pl.polsl.s15.library.api.controller.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.api.response.permissions.PermissionsResponseDTO;
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
    public ResponseEntity<PermissionsResponseDTO> getUserRoles(@PathVariable("userId") long userId) {
        userServices.get
    }
}
