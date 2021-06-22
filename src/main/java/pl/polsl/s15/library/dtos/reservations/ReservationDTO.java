package pl.polsl.s15.library.dtos.reservations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.commons.enums.ReservationStatus;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.dtos.users.ClientDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ReservationDTO {
    private Long id;
    private LocalDate endTime;
    private RentalBook rentalBook;
    private Boolean returned;
    private ReservationStatus status;
}
