package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.commons.exceptions.books.BookAlreadyFreeException;
import pl.polsl.s15.library.commons.exceptions.books.BookAlreadyOccupiedException;
import pl.polsl.s15.library.commons.exceptions.books.NoSuchBookException;
import pl.polsl.s15.library.domain.reservations.Reservation;
import pl.polsl.s15.library.domain.stock.ItemPhoto;
import pl.polsl.s15.library.domain.stock.books.Book;
import pl.polsl.s15.library.domain.stock.books.BookDetails;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.dtos.stock.books.BookBasicDTO;
import pl.polsl.s15.library.dtos.stock.books.BookFullDTO;
import pl.polsl.s15.library.repository.BookRepository;
import pl.polsl.s15.library.repository.RentalBookRepository;
import pl.polsl.s15.library.repository.ReservationRepository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    @Autowired
    public BookService(RentalBookRepository rentalBookRepository, BookRepository bookRepository, ReservationRepository reservationRepository) {
        this.rentalBookRepository = rentalBookRepository;
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
    }

    private final BookRepository bookRepository;

    private final RentalBookRepository rentalBookRepository;

    private final ReservationRepository reservationRepository;

    private Long calculateNumberOfOccupiedRentalBook(Book book) {
        long desiredBookDetailsId = book.getDetails().getId();
        return rentalBookRepository.countOccupiedBooksForGivenBookDetails(desiredBookDetailsId);
    }

    private Long calculateNumberOfBooksForGivenDetailsId(Long bookDetailsId) {
        return rentalBookRepository.countRentalBookByDetails_Id(bookDetailsId);
    }

    private BookFullDTO assignToBookDTO(Book book) {
        if (book instanceof RentalBook) {
            RentalBook rentalBook = (RentalBook) book;
            Long totalNumberOfBooks = calculateNumberOfBooksForGivenDetailsId(book.getDetails().getId());
            Long numberOfOccupiedBooks = calculateNumberOfOccupiedRentalBook(book);

            return BookFullDTO.builder()
                    .bookId(book.getId())
                    .serialNumber(rentalBook.getSerialNumber())
                    .name(book.getDetails().getName())
                    .author(book.getDetails().getAuthor())
                    .publisher(book.getDetails().getName())
                    .description(book.getDescription())
                    .publicationDate(book.getDetails().getPublicationDate())
                    .isOccupied(rentalBook.getIsOccupied())
                    .numberOfBooks(totalNumberOfBooks)
                    .numberOfOccupiedBooks(numberOfOccupiedBooks)
                    .build();
        } else {
            Long totalNumberOfBooks = calculateNumberOfBooksForGivenDetailsId(book.getDetails().getId());
            return BookFullDTO.builder()
                    .bookId(book.getId())
                    .serialNumber(null)
                    .name(book.getDetails().getName())
                    .author(book.getDetails().getAuthor())
                    .publisher(book.getDetails().getName())
                    .description(book.getDescription())
                    .publicationDate(book.getDetails().getPublicationDate())
                    .isOccupied(null)
                    .numberOfBooks(totalNumberOfBooks)
                    .numberOfOccupiedBooks(null)
                    .build();
        }

    }

    private BookBasicDTO assignToBookBasicDTO(Book book) {
        if (book instanceof RentalBook) {
            RentalBook rentalBook = (RentalBook) book;
            Long totalNumberOfBooks = calculateNumberOfBooksForGivenDetailsId(book.getDetails().getId());
            Long numberOfOccupiedBooks = calculateNumberOfOccupiedRentalBook(book);

            return new BookBasicDTO(
                    book.getId(),
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    book.getPhotos().stream().map(ItemPhoto::getUrl).collect(Collectors.toList()),
                    rentalBook.getIsOccupied(),
                    totalNumberOfBooks,
                    numberOfOccupiedBooks
            );
        } else {
            Long totalNumberOfBooks = calculateNumberOfBooksForGivenDetailsId(book.getDetails().getId());
            return new BookBasicDTO(
                    book.getId(),
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    book.getPhotos().stream().map(ItemPhoto::getUrl).collect(Collectors.toList()),
                    null,
                    totalNumberOfBooks,
                    null
            );
        }
    }

    private Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException(id));
    }

    public Page<BookFullDTO> findAllFull(Pageable pageable) {
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

    public BookFullDTO findFullById(Long id) {
        return assignToBookDTO(findById(id));
    }

    @Transactional(readOnly = false)
    public void addBook(RentalBook rentalBook) {
        rentalBookRepository.save(rentalBook);
    }

    @Transactional(readOnly = false)
    public void removeBook(long serialNumber) {
        List<Reservation> reservations = reservationRepository.findAllByBookSerial(serialNumber);
        for (Reservation reservation:reservations) {
            reservation.setRentalBook(null);
            reservationRepository.save(reservation);
        }
        rentalBookRepository.deleteBySerialNumber(serialNumber);
    }

    @Transactional(readOnly = false)
    public void updateBook(long serialNumber, BookDetails details, String description) {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if (optRentalBook.isPresent()) {
            RentalBook rentalBook = optRentalBook.get();
            rentalBook.SetDetailsIfChanged(details);
            if (description != null)
                rentalBook.setDescription(description);
            rentalBookRepository.save(rentalBook);
        } else
            throw new NoSuchBookException(serialNumber);
    }

    @Transactional(readOnly = false)
    public void occupyBook(long serialNumber) {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if (optRentalBook.isPresent()) {
            RentalBook rentalBook = optRentalBook.get();
            boolean result = rentalBook.Occupy();
            if (result)
                rentalBookRepository.save(rentalBook);
            else
                throw new BookAlreadyOccupiedException(serialNumber);
        } else
            throw new NoSuchBookException(serialNumber);
    }

    @Transactional(readOnly = false)
    public void freeBook(long serialNumber) {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if (optRentalBook.isPresent()) {
            RentalBook rentalBook = optRentalBook.get();
            boolean result = rentalBook.Free();
            if (result)
                rentalBookRepository.save(rentalBook);
            else
                throw new BookAlreadyFreeException(serialNumber);
        } else
            throw new NoSuchBookException(serialNumber);
    }
    public boolean checkIfExists(long serialNumber)
    {
        return rentalBookRepository.existsBySerialNumber(serialNumber);
    }
}
