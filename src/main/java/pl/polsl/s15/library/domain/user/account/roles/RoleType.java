package pl.polsl.s15.library.domain.user.account.roles;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {

    USER("USER"),
    EMPLOYEE("EMPLOYEE"),
    CLIENT("CLIENT"),
    MANAGER("MANAGER");

    private String value;
}
