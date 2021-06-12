package pl.polsl.s15.library.dtos.reservations;

import lombok.AllArgsConstructor;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.domain.stock.books.Book;
import pl.polsl.s15.library.domain.stock.books.RentalBook;

import java.time.LocalDateTime;
public class OrderItemResponseDTO {
    private Long itemId;
    private String title;
    private String author;
    private String photoUrl;
    private LocalDateTime end_date;
    public OrderItemResponseDTO(Long itemID,LocalDateTime end_date)
    {
        this.itemId = itemID;
        this.end_date = end_date;
    }
    public void Fill(RentalBook rentalBook)
    {
        this.title = ((Book)rentalBook).getDetails().getName();
        this.author = ((Book)rentalBook).getDetails().getAuthor();
        if(!((Book)rentalBook).getPhotos().isEmpty())
            this.photoUrl = ((Book)rentalBook).getPhotos().get(0).getUrl();
    }
}
