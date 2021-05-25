package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.domain.stock.books.RentalBook;

import java.util.Optional;


@Repository
public interface RentalBookRepository extends JpaRepository<RentalBook, Long> {
    Optional<RentalBook> findBySerialNumber(Long serialNumber);

    @Modifying
    @Transactional
    @Query("DELETE FROM RentalBook r WHERE r.serialNumber = :serial")
    void deleteBySerialNumber(@Param("serial") long serialNumber);
}