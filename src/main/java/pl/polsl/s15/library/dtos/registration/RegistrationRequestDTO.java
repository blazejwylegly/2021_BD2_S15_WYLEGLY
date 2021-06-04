package pl.polsl.s15.library.dtos.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequestDTO {
    private String username;
    private String password;
    private String email;
}
