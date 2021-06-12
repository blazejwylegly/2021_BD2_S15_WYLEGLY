package pl.polsl.s15.library.dtos.reservations;

import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;

import java.time.LocalDateTime;

public class OrderItemDTO {
    private long bookID;
    private LocalDateTime end_date;
    public OrderItem getOrderItem(Cart cart)
    {
        return new OrderItem(bookID,end_date,cart);
    }
}
