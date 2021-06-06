package pl.polsl.s15.library.dtos.ordering;

import lombok.Builder;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.dtos.users.ClientDTO;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public class CartDTO {
    private Long id;
    private List<OrderItemDTO> orderItems;
    private ClientDTO clientDTO;

    public static CartDTO of(Cart cart) {
        List<OrderItemDTO> orderItems = mapOrderItemsToDTO(cart.getOrderItems());

        return CartDTO.builder()
                .id(cart.getId())
                .clientDTO(cart.getClient())
    }

    private List<OrderItemDTO> mapOrderItemsToDTO(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemDTO::of)
                .collect(Collectors.toList());
    }
}
