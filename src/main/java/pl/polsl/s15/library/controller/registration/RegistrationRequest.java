package pl.polsl.s15.library.controller.registration;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
}
