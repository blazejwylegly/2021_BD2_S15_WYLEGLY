package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.s15.library.domain.stock.books.BookDetails;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails,Long> {
}
