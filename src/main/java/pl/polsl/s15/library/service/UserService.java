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
import pl.polsl.s15.library.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByCredentials_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User %s does not exist", username)
                ));
    }

    public Optional<User> loadUserByEmail(String email) {
        return repository.findByCredentials_EmailAddress(email);
    }

    public void createUser(User user) throws UserAlreadyRegisteredException {
        log.info("Attempting to create user {}", user.getUsername());
        throwIfUserExists(user);
        repository.save(user);
        log.info("User {} creation successful", user.getUsername());
    }

    private void throwIfUserExists(User user) throws UserAlreadyRegisteredException {
        if(repository.existsByCredentials_Username(user.getUsername()))
            throw new UserAlreadyRegisteredException(
                    String.format("User with given username [%s] already exists!", user.getUsername())
            );
        if(repository.existsByCredentials_EmailAddress(user.getCredentials().getEmailAddress()))
            throw new UserAlreadyRegisteredException(
                    String.format("User with given email address [%s] already exists!", user.getCredentials().getEmailAddress())
            );
    }

}