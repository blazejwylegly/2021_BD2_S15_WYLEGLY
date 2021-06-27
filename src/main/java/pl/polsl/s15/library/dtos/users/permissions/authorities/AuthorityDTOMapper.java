package pl.polsl.s15.library.dtos.users.permissions.authorities;

import pl.polsl.s15.library.domain.user.account.roles.Authority;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthorityDTOMapper {

    public static Set<Authority> toEntity(Set<AuthorityDTO> authorities) {
        return authorities.stream()
                .map(AuthorityDTOMapper::toEntity)
                .collect(Collectors.toSet());
    }

    public static Authority toEntity(AuthorityDTO authorityDTO) {
        return Authority.builder()
                .id(authorityDTO.getId())
                .authority(authorityDTO.getName())
                .build();
    }

    public static Set<AuthorityDTO> toDTO(Set<Authority> authorities) {
        return authorities.stream()
                .map(AuthorityDTOMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public static AuthorityDTO toDTO(Authority authority) {
        return AuthorityDTO.builder()
                .id(authority.getId())
                .name(authority.getAuthority())
                .build();
    }

}
