package pl.polsl.s15.library.dtos.reservations;

import lombok.Getter;
import pl.polsl.s15.library.domain.stock.books.RentalBook;

import java.time.LocalDate;
@Getter
public class OrderItemResponseDTO {
    private Long itemId;
    private String title;
    private String author;
    private String photoUrl;
    private LocalDate endDate;
    public OrderItemResponseDTO(Long itemID,LocalDate endDate)
    {
        this.itemId = itemID;
        this.endDate = endDate;
    }
    public void Fill(RentalBook rentalBook)
    {
        this.title = (rentalBook).getDetails().getName();
        this.author = (rentalBook).getDetails().getAuthor();
        if(!(rentalBook).getPhotos().isEmpty())
            this.photoUrl = (rentalBook).getPhotos().get(0).getUrl();
    }
}
