package pl.polsl.s15.library.dtos.users.permissions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.domain.user.account.roles.Authority;

@Getter
@Builder
@AllArgsConstructor
public class AuthorityDTO {
    private Long id;
    private String authority;

    private AuthorityDTO(String authority) {
        this.authority = authority;
    }

    public static AuthorityDTO of(String authority) {
        return new AuthorityDTO(authority);
    }

    public static AuthorityDTO of(Authority authority) {
        return AuthorityDTO.builder()
                .id(authority.getId())
                .authority(authority.getAuthority())
                .build();
    }
}
