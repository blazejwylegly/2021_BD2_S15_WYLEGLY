package pl.polsl.s15.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.s15.library.domain.stock.books.RentalBook;

import java.util.Optional;

@Repository
public interface RentalBookRepository extends CrudRepository<RentalBook, Long> {
    Optional<RentalBook> findBySerialNumber(Long serialNumber);
}
