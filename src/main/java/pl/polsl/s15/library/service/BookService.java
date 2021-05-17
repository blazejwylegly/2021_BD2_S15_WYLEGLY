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
import pl.polsl.s15.library.dtos.BookFullDTO;
import pl.polsl.s15.library.exception.NoSuchBookException;
import pl.polsl.s15.library.repository.BookRepository;
import pl.polsl.s15.library.repository.RentalBookRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final RentalBookRepository rentalBookRepository;

    private final ItemPhotoService itemPhotoService;

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

    private BookFullDTO assignToBookFullDTO(Book book) {
        if (book instanceof RentalBook) {
            RentalBook rentalBook = (RentalBook) book;
            return new BookFullDTO(
                    book.getId(),
                    Optional.ofNullable(rentalBook.getSerialNumber()),
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    Optional.of(book.getDetails().getPublisher()),
                    itemPhotoService.convertBufferedImageToByte(itemPhotoService.getPhotos(book)),
                    book.getDescription(),
                    Optional.ofNullable(book.getDetails().getPublicationDate()),
                    rentalBook.getIsOccupied(),
                    rentalBook.getDetails().getBooks().size(),
                    Optional.of(calculateNumberOfOccupiedRentalBook(book))
            );
        } else {
            return new BookFullDTO(
                    book.getId(),
                    null,
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    Optional.of(book.getDetails().getPublisher()),
                    itemPhotoService.convertBufferedImageToByte(itemPhotoService.getPhotos(book)),
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
                    itemPhotoService.getPhotos(book).stream().map(photo->
                            itemPhotoService.convertBufferedImageToByte(
                                    itemPhotoService.resizeImage(photo,100,100)
                            )
                    ).collect(Collectors.toList()),
                    rentalBook.getIsOccupied(),
                    rentalBook.getDetails().getBooks().size(),
                    Optional.of(calculateNumberOfOccupiedRentalBook(book))
            );
        } else {
            return new BookBasicDTO(
                    book.getId(),
                    book.getDetails().getName(),
                    book.getDetails().getAuthor(),
                    itemPhotoService.getPhotos(book).stream().map(photo->
                            itemPhotoService.convertBufferedImageToByte(
                                    itemPhotoService.resizeImage(photo,100,100)
                            )
                    ).collect(Collectors.toList()),
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

    public Page<BookFullDTO> findAllFull(Pageable pageable) {
        final Page<Book> books = findAll(pageable);
        return books.map(book -> assignToBookFullDTO(book));
    }

    public Page<BookBasicDTO> findAllBasic(Pageable pageable) {
        final Page<Book> books = findAll(pageable);
        return books.map(book -> assignToBookBasicDTO(book));
    }


    public BookBasicDTO findBasicById(Long id) {
        return assignToBookBasicDTO(findById(id));
    }

    public BookFullDTO findFullById(Long id) {
        return assignToBookFullDTO(findById(id));
    }
}
