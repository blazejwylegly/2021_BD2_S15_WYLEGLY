package pl.polsl.s15.library.controller.login.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;
    private String email;

    public Optional<String> getUsername() {
        return Optional.ofNullable(this.username);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(this.email);
    }

}
