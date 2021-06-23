package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.domain.stock.books.RentalBook;

import java.util.List;
import java.util.Optional;


@Repository
public interface RentalBookRepository extends JpaRepository<RentalBook, Long> {
    Optional<RentalBook> findBySerialNumber(Long serialNumber);

    void deleteBySerialNumber(long serialNumber);

    @Query("SELECT COUNT(r) FROM RentalBook r " +
            "WHERE r.details.id = :details_id " +
            "AND r.isOccupied = true " +
            "GROUP BY r.isOccupied")
    Long countOccupiedBooksForGivenBookDetails(@Param("details_id") long details_id);

    @Query("SELECT r FROM RentalBook r "+
            "WHERE r.details.id = :details_id "+
            "AND r.isOccupied = false ")
    List<RentalBook> findAllFreeByDetailsId(Long details_id);

    Long countRentalBookByDetails_Id(long details_id);

    Boolean existsBySerialNumber(long serialNumber);
}