package pl.polsl.s15.library.controller.login;

import lombok.Data;
import lombok.Getter;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
