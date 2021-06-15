package pl.polsl.s15.library.dtos.ordering;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.domain.ordering.OrderItem;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderItemDTO {
    private Long id;
    private Long itemId;
    private LocalDate requestedStartDate;
    private LocalDate requestedEndDate;

    public static OrderItemDTO of(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .itemId(orderItem.getItemId())
                .requestedStartDate(LocalDate.now())
                .requestedEndDate(orderItem.getRequestedEndDate())
                .build();
    }
}
