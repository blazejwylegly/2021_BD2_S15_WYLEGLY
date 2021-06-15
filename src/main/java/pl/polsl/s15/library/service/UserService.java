package pl.polsl.s15.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.polsl.s15.library.commons.exceptions.authentication.UserAlreadyRegisteredException;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.domain.user.account.roles.Authority;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.dtos.users.UsersDTOMapper;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.PermissionsDTOMapper;
import pl.polsl.s15.library.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByCredentials_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User %s does not exist", username)
                ));
    }

    public Optional<AccountPermissionsDTO> getPermissionsForUser(Long userId) {
        return userRepository.findById(userId)
                .map(User::getPermissions)
                .map(PermissionsDTOMapper::toDTO);
    }

    public Optional<User> loadUserByEmail(String email) {
        return userRepository.findByCredentials_EmailAddress(email);
    }

    public void createUser(UserDTO userDTO) throws UserAlreadyRegisteredException {
        validateIfUserExists(userDTO);
        userRepository.save(UsersDTOMapper.userDTOtoEntity(userDTO));
    }

    protected void validateIfUserExists(UserDTO userDTO) throws UserAlreadyRegisteredException {
        String username = userDTO.getAccountCredentialsDTO().getUsername();
        if (userRepository.existsByCredentials_Username(username))
            throw new UserAlreadyRegisteredException(
                    String.format("User with given username [%s] already exists!", username)
            );

        String emailAddress = userDTO.getAccountCredentialsDTO().getEmailAddress();
        if (userRepository.existsByCredentials_EmailAddress(emailAddress))
            throw new UserAlreadyRegisteredException(
                    String.format("User with given email address [%s] already exists!", emailAddress)
            );
    }
}