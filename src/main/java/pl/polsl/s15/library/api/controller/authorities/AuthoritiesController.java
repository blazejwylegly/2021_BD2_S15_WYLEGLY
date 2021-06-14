package pl.polsl.s15.library.api.controller.authorities;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.api.controller.authorities.request.AuthorityCreationRequestDTO;
import pl.polsl.s15.library.api.controller.authorities.response.GetAuthoritiesResponseDTO;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.domain.user.account.roles.Authority;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.service.AuthorityService;

import java.util.Set;

@RestController
@RequestMapping("/api/authorities")
public class AuthoritiesController {

    private AuthorityService authorityService;

    @Autowired
    public AuthoritiesController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetAuthoritiesResponseDTO> getUserAuthorities
            (@PathVariable("userId") long userId) {
        Set<AuthorityDTO> authorities = authorityService.getUserAuthorities(userId);
        return ResponseEntity.ok()
                .body(AuthorityReqRepMapper.getAuthoritiesResponseDTO(authorities));
    }

    @GetMapping("/available")
    public ResponseEntity<GetAuthoritiesResponseDTO> getAllAvailableAuthorities() {
        Set<AuthorityDTO> authorities = authorityService.getAll();
        return ResponseEntity.ok()
                .body(AuthorityReqRepMapper.getAuthoritiesResponseDTO(authorities));
    }

    @DeleteMapping("/{authorityId}")
    public ResponseEntity<ResponseDTO> deleteAuthorityById(
            @PathVariable("authorityId") long authorityId) {
        authorityService.deleteById(authorityId);
        return ResponseEntity.ok()
                .body(AuthorityReqRepMapper.authorityDeletedById(authorityId));
    }

    @DeleteMapping("/{authorityName}")
    public ResponseEntity<ResponseDTO> deleteAuthorityByName(
            @PathVariable("authorityName") String authorityName) {
        authorityService.deleteByAuthorityName(authorityName);
        return ResponseEntity.ok()
                .body(AuthorityReqRepMapper.authorityDeletedByName(authorityName));

    }

    @PostMapping("/new")
    public ResponseEntity<ResponseDTO> createNewAuthority(
            @RequestBody AuthorityCreationRequestDTO requestDTO) {
        AuthorityDTO authorityToBeCreated = AuthorityReqRepMapper.mapCreateRequestToAuthorities(requestDTO);
        authorityService.createNewAuthority(authorityToBeCreated);
        return ResponseEntity.ok()
                .body(AuthorityReqRepMapper.authoritySuccessfullyCreated(requestDTO));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDTO> updateExistingAuthority(
            @RequestBody AuthorityUpdateRequestDTO requestDTO
    ) {
        
    }
}
