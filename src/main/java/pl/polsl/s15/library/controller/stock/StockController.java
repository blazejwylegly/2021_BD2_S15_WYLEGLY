package pl.polsl.s15.library.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.s15.library.dtos.AddOrUpdateBookRequestDTO;
import pl.polsl.s15.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class StockController {
    private BookService bookService;

    @Autowired
    public StockController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    void addBook(@RequestBody AddOrUpdateBookRequestDTO request) {
        if (request.invalidAdd())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else
            bookService.addBook(request.getRentalBook());
    }

    @PostMapping("/remove")
    void removeBook(@RequestParam(name = "serialNumber") long serialNumber) {
        bookService.removeBook(serialNumber);
    }

    @PostMapping("/update")
    void updateBook(@RequestBody AddOrUpdateBookRequestDTO request) {
        if (request.invalidUpdate())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else
            bookService.updateBook(request.getSerialNumber(), request.getBookDetails(), request.getDescription());
    }

    @PostMapping("/occupy")
    void occupyBook(@RequestParam(name = "serialNumber") long serialNumber) {
        bookService.occupyBook(serialNumber);
    }

    @PostMapping("/free")
    void freeBook(@RequestParam(name = "serialNumber") long serialNumber) {
        bookService.freeBook(serialNumber);
    }
}
