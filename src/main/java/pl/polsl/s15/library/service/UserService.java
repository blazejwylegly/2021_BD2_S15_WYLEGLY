package pl.polsl.s15.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.polsl.s15.library.commons.exceptions.InvalidUpdateRequestException;
import pl.polsl.s15.library.commons.exceptions.authentication.UserAlreadyRegisteredException;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.dtos.users.UsersDTOMapper;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.PermissionsDTOMapper;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTOMapper;
import pl.polsl.s15.library.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByCredentials_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User %s does not exist", username)
                ));
    }

    public List<UserDTO> getAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map(UsersDTOMapper::userToDTO)
                .collect(Collectors.toList());
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
        validateIfUserExistsByCredentials(userDTO);
        updateRoles(userDTO);
        userRepository.save(UsersDTOMapper.userToEntity(userDTO));
    }

    private void updateRoles(UserDTO userDTO) {
        userDTO.addNewRole("USER");
    }

    protected void validateIfUserExistsByCredentials(UserDTO userDTO) throws UserAlreadyRegisteredException {
        validateIfUserExistsByUsername(
                userDTO.getAccountCredentialsDTO().getUsername()
        );
        validateIfUserExistsByEmailAddress(
                userDTO.getAccountCredentialsDTO().getEmailAddress()
        );
    }

    protected void validateIfUserExistsByUsername(String username) {
        if (userRepository.existsByCredentials_Username(username))
            throw new UserAlreadyRegisteredException(
                    String.format("User with given username [%s] already exists!", username)
            );
    }

    protected void validateIfUserExistsByEmailAddress(String emailAddress) {
        if (userRepository.existsByCredentials_EmailAddress(emailAddress))
            throw new UserAlreadyRegisteredException(
                    String.format("User with given email address [%s] already exists!", emailAddress)
            );
    }

    public void updateUser(UserDTO userDTO)
            throws InvalidUpdateRequestException {
        validateIfUpdateIsPossible(userDTO);
        User user = UsersDTOMapper.userToEntity(userDTO);
        userRepository.save(user);
    }

    private void validateIfUpdateIsPossible(UserDTO userDTO) throws InvalidUpdateRequestException {
        validateIfCredentialsAreOccupied(userDTO);
        validateIfUserExistsById(userDTO);
    }

    private void validateIfCredentialsAreOccupied(UserDTO userDTO) throws InvalidUpdateRequestException {
        try {
            validateIfUserExistsByCredentials(userDTO);
        } catch (UserAlreadyRegisteredException exception) {
            throw new InvalidUpdateRequestException(exception.getMessage());
        }
    }

    private void validateIfUserExistsById(UserDTO userDTO) throws InvalidUpdateRequestException {
        if(userRepository.findById(userDTO.getId()).isEmpty()) {
            throw new InvalidUpdateRequestException(
                    "User with id " + userDTO.getId() + "does not exist!"
            );
        }
    }

    public void addUserRole(long userId, String roleName) {
        Optional<Role> roleToBeAdded = roleService.getRoleByName(roleName)
                .map(RoleDTOMapper::toEntity);
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent() && roleToBeAdded.isPresent()) {
            user.get().addNewRole(roleToBeAdded.get());
            userRepository.save(user.get());
        }
    }

    public void deleteUserRole(long userId, String roleName) {
        Optional<Role> roleToBeDeleted = roleService.getRoleByName(roleName)
                .map(RoleDTOMapper::toEntity);
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent() && roleToBeDeleted.isPresent()) {
            user.get().deleteRole(roleToBeDeleted.get());
            userRepository.save(user.get());
        }
    }

    public void deleteUserById(long clientIdToBeDeleted) {
        userRepository.deleteById(clientIdToBeDeleted);
    }
}
