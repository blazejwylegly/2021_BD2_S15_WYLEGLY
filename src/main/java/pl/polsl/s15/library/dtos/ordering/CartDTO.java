package pl.polsl.s15.library.dtos.ordering;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.dtos.users.ClientDTO;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CartDTO {

    private ClientDTO clientDTO;
    private Long id;
    private List<OrderItemDTO> orderItems;

    public static CartDTO ofEntity(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .build();
    }

    private static List<OrderItemDTO> mapOrderItemsToDTO(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemDTO::of)
                .collect(Collectors.toList());
    }
}
