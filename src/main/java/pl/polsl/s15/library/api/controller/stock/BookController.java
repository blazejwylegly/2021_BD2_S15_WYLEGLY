package pl.polsl.s15.library.controller.stock;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.dtos.stock.books.BookBasicDTO;
import pl.polsl.s15.library.dtos.stock.books.BookFullDTO;
import pl.polsl.s15.library.service.BookService;

@AllArgsConstructor
@Validated
@RequestMapping("/api/books")
@RestController
public class BookController {

    private BookService bookService;

    @Operation(summary = "Get full info about books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All information about the books was returned"),
            @ApiResponse(code = 400, message = "Cannot return all info about books")
    })
    @GetMapping(value = "/full")
    @ResponseStatus(HttpStatus.OK)
    public Page<BookFullDTO> findAllFull(@PageableDefault Pageable pageable) {
        return bookService.findAllFull(pageable);
    }

    @Operation(summary = "Get basic info about books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Basic information about the books was returned"),
            @ApiResponse(code = 400, message = "Cannot return all basic about books")
    })
    @GetMapping(value = "/basic")
    public Page<BookBasicDTO> findAllBasic(@PageableDefault Pageable pageable){
        return bookService.findAllBasic(pageable);
    }

    @Operation(summary = "Get basic info about books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Basic information about the books was returned"),
            @ApiResponse(code = 400, message = "Cannot return all basic about books")
    })
    @ResponseBody
    @GetMapping(value = "/basic/{id}")
    public BookBasicDTO findBasicById(@PathVariable Long id){
        return bookService.findBasicById(id);
    }

    @Operation(summary = "Get full info about books with id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Full information about the books was returned"),
            @ApiResponse(code = 404, message = "Cannot return all full about books")
    })
    @ResponseBody
    @GetMapping(value = "/full/{id}")
    public BookFullDTO findFullById(@PathVariable Long id) {
        return bookService.findFullById(id);
    }

}
