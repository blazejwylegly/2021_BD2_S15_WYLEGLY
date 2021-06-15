package pl.polsl.s15.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.polsl.s15.library.commons.exceptions.user.permissions.AuthorityAlreadyExistsException;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.authorities.AuthorityDTOMapper;
import pl.polsl.s15.library.repository.AuthorityRepository;
import pl.polsl.s15.library.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorityService {

    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository,
                            UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    public Set<AuthorityDTO> getAll() {
        return authorityRepository.findAll()
                .stream()
                .map(AuthorityDTOMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public Set<AuthorityDTO> getUserAuthorities(@PathVariable("userId") long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getPermissions().getAuthorities())
                .map(AuthorityDTOMapper::toDTO)
                .orElse(Collections.emptySet());
    }

    public void deleteById(Long authorityId) {
        authorityRepository.deleteById(authorityId);
    }

    public void deleteByAuthorityName(String authorityName) {
        authorityRepository.deleteByAuthority(authorityName);
    }

    public void createNewAuthority(AuthorityDTO authorityToBeCreated) {
        if(authorityExists(authorityToBeCreated))
            throw new AuthorityAlreadyExistsException(
                    String.format("Authority '%s' already exists!", authorityToBeCreated.getName()));
        authorityRepository.save(AuthorityDTOMapper.toEntity(authorityToBeCreated));
    }

    public boolean authorityExists(AuthorityDTO authorityDTO) {
        return authorityRepository.existsByAuthority(authorityDTO.getName());
    }
}
