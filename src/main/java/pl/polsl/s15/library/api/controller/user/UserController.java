package pl.polsl.s15.library.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.api.controller.base.BaseController;
import pl.polsl.s15.library.api.controller.user.response.GetAccountMetaDataResponse;
import pl.polsl.s15.library.api.controller.user.response.GetAllUsersResponse;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.dtos.users.meta.AccountMetaData;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.PermissionsDTOMapper;
import pl.polsl.s15.library.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/meta")
    public ResponseEntity<GetAccountMetaDataResponse> getAccountMeta() {
        Authentication user = getCurrentUser();
        AccountMetaData accountMetaData = prepareAccountMetaData((String) user.getPrincipal());
        return ResponseEntity.ok()
                .body(UserReqRepMapper.getAccountMetaDataResponse(accountMetaData));
    }

    private AccountMetaData prepareAccountMetaData(String principal) {
        User user = (User) userService.loadUserByUsername(principal);
        long userId = user.getId();
        AccountPermissionsDTO permissions = PermissionsDTOMapper.toDTO(user.getPermissions());
        return AccountMetaData.builder()
                .userId(userId)
                .permissionsDTO(permissions)
                .build();
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        List<UserDTO> users = userService.getAll();
        return ResponseEntity.ok()
                .body(UserReqRepMapper.getAllUsersResponse(users));
    }
}
