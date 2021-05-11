package pl.polsl.s15.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.s15.library.domain.stock.books.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {

}
