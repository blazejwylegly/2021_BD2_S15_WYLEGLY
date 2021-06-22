package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.polsl.s15.library.domain.user.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c "+
            "WHERE c.cart.id = :cart_id ")
    Optional<Client> findClientByCartId(Long cart_id);

    @Query("SELECT c FROM Client c "+
            "JOIN c.reservations r " +
            "WHERE r.id = :reservation_id ")
    Optional<Client> findClientByReservationId(Long reservation_id);

}
