package pl.polsl.s15.library.dtos.ordering;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.domain.ordering.OrderItem;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderItemDTO {
    private Long id;
    private Long itemId;
    private LocalDateTime requestedStartDate;
    private LocalDateTime requestedEndDate;

    public static OrderItemDTO of(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .itemId(orderItem.getItemId())
                .requestedStartDate(orderItem.getRequestedStartDate())
                .requestedEndDate(orderItem.getRequestedEndDate())
                .build();
    }
}
