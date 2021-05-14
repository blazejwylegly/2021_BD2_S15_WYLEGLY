package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import pl.polsl.s15.library.domain.stock.books.BookDetails;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.repository.BookRepository;
import pl.polsl.s15.library.repository.RentalBookRepository;

import java.time.LocalDate;
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

    public void addBook(String name,
                        String author,
                        String publisher,
                        String publicationDate,
                        String description,
                        long serialNumber)
    {
        LocalDate localDate;
        if(publicationDate!= null)
            localDate = LocalDate.parse(publicationDate);
        else
            localDate = null;
        BookDetails details = new BookDetails(name,author,publisher,localDate);
        RentalBook rentalBook = new RentalBook(details,description,serialNumber);
        rentalBookRepository.save(rentalBook);
    }
    public void removeBook(long serialNumber)
    {
        rentalBookRepository.deleteBySerialNumber(serialNumber);
    }
    @Transactional
    public void updateBook(String name,
                           String author,
                           String publisher,
                           String publicationDate,
                           String description,
                           long serialNumber)
    {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if(optRentalBook.isPresent())
        {
            RentalBook rentalBook = optRentalBook.get();
            BookDetails newDetails = new BookDetails();
            boolean updated = false;
            if(name!=null) {
                newDetails.name = name;
                updated = true;
            }
            if(author!=null) {
                newDetails.author = author;
                updated = true;
            }
            if(publisher!=null) {
                newDetails.publisher = publisher;
                updated = true;
            }
            if(publicationDate!=null) {
                newDetails.publicationDate = LocalDate.parse(publicationDate);
                updated = true;
            }
            if(description!=null)
                rentalBook.setDescription(description);
            if(updated)
                rentalBook.setDetails(newDetails);
            rentalBookRepository.save(rentalBook);
        }
    }
    @Transactional
    public long occupyBook(long serialNumber)
    {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if(optRentalBook.isPresent())
        {
            RentalBook rentalBook = optRentalBook.get();
            boolean result = rentalBook.Occupy();
            if(result)
            {
                rentalBookRepository.save(rentalBook);
                return 0;
            }
            return 1;
        }
        return 2;
    }
    @Transactional
    public long freeBook(long serialNumber)
    {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findBySerialNumber(serialNumber);
        if(optRentalBook.isPresent())
        {
            RentalBook rentalBook = optRentalBook.get();
            boolean result = rentalBook.Free();
            if(result)
            {
                rentalBookRepository.save(rentalBook);
                return 0;
            }
            return 1;
        }
        return 2;
    }
}
