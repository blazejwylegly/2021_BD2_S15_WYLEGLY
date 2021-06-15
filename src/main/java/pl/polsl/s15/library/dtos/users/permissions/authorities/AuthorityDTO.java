package pl.polsl.s15.library.dtos.users.permissions.authorities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.domain.user.account.roles.Authority;

@Getter
@Builder
@AllArgsConstructor
public class AuthorityDTO {
    private Long id;
    private String name;

    private AuthorityDTO(String name) {
        this.name = name;
    }

    public static AuthorityDTO of(String authority) {
        return new AuthorityDTO(authority);
    }

    public static AuthorityDTO of(Authority authority) {
        return AuthorityDTO.builder()
                .id(authority.getId())
                .name(authority.getAuthority())
                .build();
    }
}
