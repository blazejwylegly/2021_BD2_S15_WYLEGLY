package pl.polsl.s15.library.controller.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AuthResponseDTO {
    private String username;
    private String msg;
}
