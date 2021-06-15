package pl.polsl.s15.library.api.controller.authorities;

import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.authorities.request.AuthorityCreationRequestDTO;
import pl.polsl.s15.library.api.controller.authorities.response.GetAuthoritiesResponseDTO;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorityReqRepMapper {
    public static GetAuthoritiesResponseDTO getAuthoritiesResponseDTO(Set<AuthorityDTO> authorities) {
        return GetAuthoritiesResponseDTO.getAuthoritiesResponseBuilder()
                .status(HttpStatus.OK)
                .message("Successfully retrieved roles")
                .timestamp(new Date())
                .authorities(getAuthoritiesInPlainText(authorities))
                .build();
    }

    private static Set<String> getAuthoritiesInPlainText(Set<AuthorityDTO> authorities) {
        return authorities
                .stream()
                .map(AuthorityDTO::getName)
                .collect(Collectors.toSet());
    }

    public static ResponseDTO authorityDeletedById(long authorityId) {
        return authorityDeleted(
                String.format("Successfully deleted authority with id %s", authorityId)
        );
    }

    public static ResponseDTO authorityDeletedByName(String name) {
        return authorityDeleted(
                String.format("Successfully deleted %s authority", name)
        );
    }

    private static ResponseDTO authorityDeleted(String msg) {
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message(msg)
                .timestamp(new Date())
                .build();

    }

    public static AuthorityDTO mapCreateRequestToAuthorities(AuthorityCreationRequestDTO requestDTO) {
        return AuthorityDTO.builder()
                .name(requestDTO.getAuthorityName())
                .build();
    }

    public static ResponseDTO authoritySuccessfullyCreated(AuthorityCreationRequestDTO requestDTO) {
        return ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message("Successfully created authority " + requestDTO.getAuthorityName())
                .timestamp(new Date())
                .build();
    }
}
