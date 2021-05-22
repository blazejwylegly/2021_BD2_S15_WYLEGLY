package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.s15.library.domain.stock.books.BookDetails;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.exception.BookAlreadyFreeException;
import pl.polsl.s15.library.exception.BookAlreadyOccupiedException;
import pl.polsl.s15.library.exception.NoSuchBookException;
import pl.polsl.s15.library.repository.BookRepository;
import pl.polsl.s15.library.repository.RentalBookRepository;

import java.util.Optional;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    @Autowired
    public BookService(RentalBookRepository rentalBookRepository,BookRepository bookRepository) {
        this.rentalBookRepository = rentalBookRepository;
        this.bookRepository = bookRepository;
    }
    private final BookRepository bookRepository;
    private final RentalBookRepository rentalBookRepository;

    public void addBook(RentalBook rentalBook)
    {
        rentalBookRepository.save(rentalBook);
    }
    public void removeBook(long serialNumber)
    {
        rentalBookRepository.deleteBySerialNumber(serialNumber);
    }
    @Transactional
    public void updateBook(long serialNumber, BookDetails details, String description)
    {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if(optRentalBook.isPresent())
        {
            RentalBook rentalBook = optRentalBook.get();
            rentalBook.SetDetailsIfChanged(details);
            if(description!=null)
            rentalBook.setDescription(description);
            rentalBookRepository.save(rentalBook);
        }
        else
            throw new NoSuchBookException(serialNumber);
    }
    @Transactional
    public void occupyBook(long serialNumber)
    {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if(optRentalBook.isPresent())
        {
            RentalBook rentalBook = optRentalBook.get();
            boolean result = rentalBook.Occupy();
            if(result)
                rentalBookRepository.save(rentalBook);
            else
                throw new BookAlreadyOccupiedException(serialNumber);
        }
        else
            throw new NoSuchBookException(serialNumber);
    }
    @Transactional
    public void freeBook(long serialNumber)
    {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if(optRentalBook.isPresent())
        {
            RentalBook rentalBook = optRentalBook.get();
            boolean result = rentalBook.Free();
            if(result)
                rentalBookRepository.save(rentalBook);
            else
                throw new BookAlreadyFreeException(serialNumber);
        }
        else
            throw new NoSuchBookException(serialNumber);
    }
}
