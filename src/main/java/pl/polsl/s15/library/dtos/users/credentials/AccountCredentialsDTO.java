package pl.polsl.s15.library.dtos.users.credentials;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountCredentialsDTO {
    private Long id;
    private String username;
    private String password;
    private String emailAddress;
}
