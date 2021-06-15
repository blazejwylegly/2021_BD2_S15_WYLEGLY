package pl.polsl.s15.library.dtos.reservations;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;

import java.time.LocalDate;

@Getter
@Builder
public class OrderItemDTO {
    private long bookID;
    private LocalDate end_date;
}
