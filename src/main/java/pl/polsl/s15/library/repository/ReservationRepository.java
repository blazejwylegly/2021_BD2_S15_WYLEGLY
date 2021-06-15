package pl.polsl.s15.library.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.s15.library.domain.reservations.Reservation;
import pl.polsl.s15.library.domain.user.User;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findAllByClientId(long clientID);
}
