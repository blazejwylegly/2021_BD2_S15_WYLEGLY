package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.s15.library.domain.user.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
