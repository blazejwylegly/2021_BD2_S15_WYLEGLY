package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.polsl.s15.library.domain.reservations.Reservation;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("SELECT r FROM Client c " +
            "JOIN c.reservations r " +
            "WHERE c.id = :clientID")
    List<Reservation> findAllByClientId(long clientID);
}
