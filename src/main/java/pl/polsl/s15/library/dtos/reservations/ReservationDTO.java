package pl.polsl.s15.library.dtos.reservations;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.dtos.users.ClientDTO;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReservationDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ClientDTO clientDTO;
    private RentalBook rentalBook;
}
