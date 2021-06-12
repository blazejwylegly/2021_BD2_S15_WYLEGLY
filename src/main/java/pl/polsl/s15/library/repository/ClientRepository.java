package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.user.Client;

import java.util.Optional;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {
}
