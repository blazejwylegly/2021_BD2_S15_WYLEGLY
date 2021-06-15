package pl.polsl.s15.library.api.controller.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.api.response.PermissionsResponseDTO;
import pl.polsl.s15.library.commons.exceptions.user.UserNotFoundException;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private UserService userService;
    private PermissionDTOMapper dtoMapper;

    @Autowired
    public PermissionController(UserService userService, PermissionDTOMapper dtoMapper) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PermissionsResponseDTO> getUserAuthorities(@PathVariable("userId") long userId) {
        Optional<AccountPermissionsDTO> permissions = userService.getPermissionsForUser(userId);
        PermissionsResponseDTO response = permissions
                .map(dtoMapper::permissionsResponse)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found!"));
        return ResponseEntity.ok(response);
    }
}
