package pl.polsl.s15.library.api.controller.user.request;

import lombok.Data;
import pl.polsl.s15.library.commons.annotations.AuthRequest;

import java.util.Optional;

@AuthRequest
@Data
public class LoginRequestDTO {
    private String username;
    private String password;
    private String email;
}
