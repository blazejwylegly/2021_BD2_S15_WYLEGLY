package pl.polsl.s15.library.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RegistrationRequestDTO {
    private String username;
    private String password;
    private String email;

    private String firstName;
    private String lastName;
    private String photoUrl;

    private Set<String> authorities = new HashSet<>();
    private Set<String> roles = new HashSet<>();
}
