package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.domain.stock.ItemPhoto;
import pl.polsl.s15.library.domain.stock.books.Book;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.dtos.BookBasicDTO;
import pl.polsl.s15.library.dtos.BookDTO;
import pl.polsl.s15.library.exception.NoSuchBookException;
import pl.polsl.s15.library.repository.BookRepository;
import pl.polsl.s15.library.repository.RentalBookRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final RentalBookRepository rentalBookRepository;

    private Long calculateNumberOfOccupiedRentalBook(Book book) {
        Long number = 0L;
        for (Book b : book.getDetails().getBooks()) {
            if (b instanceof RentalBook) {
                RentalBook tmp = (RentalBook) b;
                if (tmp.getIsOccupied()) number++;
            }
        }
        return number;
    }

    private BookDTO assignToBookDTO(Book book) {
        if (book instanceof RentalBook) {
            RentalBook rentalBook = (RentalBook) book;
            return new BookDTO(
                    book.getId(),
                    Optional.ofNullable(rentalBook.getSerialNumber()),
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    Optional.of(book.getDetails().getPublisher()),
                    book.getPhotos().stream().map(ItemPhoto::getUrl).collect(Collectors.toList()),
                    book.getDescription(),
                    Optional.ofNullable(book.getDetails().getPublicationDate()),
                    rentalBook.getIsOccupied(),
                    rentalBook.getDetails().getBooks().size(),
                    Optional.of(calculateNumberOfOccupiedRentalBook(book))
            );
        } else {
            return new BookDTO(
                    book.getId(),
                    null,
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    Optional.of(book.getDetails().getPublisher()),
                    book.getPhotos().stream().map(ItemPhoto::getUrl).collect(Collectors.toList()),
                    book.getDescription(),
                    Optional.ofNullable(book.getDetails().getPublicationDate()),
                    null,
                    book.getDetails().getBooks().size(),
                    null
            );
        }

    }

    private BookBasicDTO assignToBookBasicDTO(Book book) {
        if (book instanceof RentalBook) {
            RentalBook rentalBook = (RentalBook) book;
            return new BookBasicDTO(
                    book.getId(),
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    book.getPhotos().stream().map(ItemPhoto::getUrl).collect(Collectors.toList()),
                    rentalBook.getIsOccupied(),
                    rentalBook.getDetails().getBooks().size(),
                    Optional.of(calculateNumberOfOccupiedRentalBook(book))
            );
        } else {
            return new BookBasicDTO(
                    book.getId(),
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    book.getPhotos().stream().map(ItemPhoto::getUrl).collect(Collectors.toList()),
                    null,
                    book.getDetails().getBooks().size(),
                    null
            );
        }
    }

    private Page<Book> findAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    private Book findById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException(id));
    }

    public Page<BookDTO> findAllFull(Pageable pageable) {
        final Page<Book> books = findAll(pageable);
        return books.map(book -> assignToBookDTO(book));
    }

    public Page<BookBasicDTO> findAllBasic(Pageable pageable) {
        final Page<Book> books = findAll(pageable);
        return books.map(book -> assignToBookBasicDTO(book));
    }


    public BookBasicDTO findBasicById(Long id) {
        return assignToBookBasicDTO(findById(id));
    }

    public BookDTO findFullById(Long id) {
        return assignToBookDTO(findById(id));
    }
}
