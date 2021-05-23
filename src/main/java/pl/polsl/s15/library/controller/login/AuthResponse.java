package pl.polsl.s15.library.controller.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AuthResponse {
    private String username;
    private String msg;
}
