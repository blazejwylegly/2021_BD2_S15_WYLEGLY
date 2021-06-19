package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.polsl.s15.library.commons.enums.ReservationStatus;
import pl.polsl.s15.library.domain.reservations.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("SELECT r FROM Client c " +
            "JOIN c.reservations r " +
            "WHERE c.id = :clientID")
    List<Reservation> findAllByClientId(long clientID);

    List<Reservation> findAllByStatus(ReservationStatus status);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.rentalBook.serialNumber = :serialNumber " +
            "AND r.status IN (1,3)")
    Optional<Reservation> findByBookSerial(long serialNumber);
}
