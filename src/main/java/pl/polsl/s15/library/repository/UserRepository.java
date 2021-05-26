package pl.polsl.s15.library.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.s15.library.domain.user.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByCredentials_Username(String username);
    Optional<User> findByCredentials_EmailAddress(String email);
    Boolean existsByCredentials_Username(String username);
    Boolean existsByCredentials_EmailAddress(String email);
}
