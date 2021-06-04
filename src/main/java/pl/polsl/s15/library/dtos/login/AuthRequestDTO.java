package pl.polsl.s15.library.dtos.login;

import lombok.Data;
import pl.polsl.s15.library.commons.annotations.AuthRequest;

import java.util.Optional;

@AuthRequest
@Data
public class AuthRequestDTO {
    private String username;
    private String password;
    private String email;
}
