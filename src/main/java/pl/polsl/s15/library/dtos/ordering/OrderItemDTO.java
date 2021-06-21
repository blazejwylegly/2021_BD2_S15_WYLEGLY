package pl.polsl.s15.library.dtos.ordering;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.domain.ordering.OrderItem;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long itemId;
    private LocalDate requestedEndDate;

    public static OrderItemDTO of(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .itemId(orderItem.getItemId())
                .requestedEndDate(orderItem.getRequestedEndDate())
                .build();
    }
}
