package pl.polsl.s15.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.s15.library.service.BookService;

@RestController
@RequestMapping("/library/api/books")
public class StockController {
    private BookService bookService;
    @Autowired
    public StockController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    void addBook(@RequestParam(name = "name")String name,
                 @RequestParam(name = "author")String author,
                 @RequestParam(name = "publisher",  required = false)String publisher,
                 @RequestParam(name = "publicationDate", required = false)String publicationDate,
                 @RequestParam(name = "description")String description,
                 @RequestParam(name = "serialNumber")long serialNumber)
    {
        bookService.addBook(name,author,publisher,publicationDate,description,serialNumber);
    }
    @PostMapping("/remove")
    void removeBook(@RequestParam(name = "serialNumber")long serialNumber)
    {
        bookService.removeBook(serialNumber);
    }
    @PostMapping("/update")
    void updateBook(@RequestParam(name = "name", required = false)String name,
                 @RequestParam(name = "author", required = false)String author,
                 @RequestParam(name = "publisher",  required = false)String publisher,
                 @RequestParam(name = "publicationDate", required = false)String publicationDate,
                 @RequestParam(name = "description", required = false)String description,
                 @RequestParam(name = "serialNumber")long serialNumber)
    {
        bookService.updateBook(name,author,publisher,publicationDate,description,serialNumber);
    }
}
