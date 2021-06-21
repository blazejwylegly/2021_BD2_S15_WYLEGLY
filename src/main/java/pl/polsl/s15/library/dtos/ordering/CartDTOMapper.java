package pl.polsl.s15.library.dtos.ordering;

import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CartDTOMapper {

    public static CartDTO toDTO(Cart cart) {
        List<OrderItemDTO> orderItems = toDTO(cart.getOrderItems());
        return CartDTO.builder()
                .id(cart.getId())
                .orderItems(orderItems)
                .build();
    }

    public static Cart toEntity(CartDTO cartDTO) {
        List<OrderItem> orderItems = toEntity(cartDTO.getOrderItems());
        return Cart.builder()
                .id(cartDTO.getId())
                .orderItems(orderItems)
                .build();
    }

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .itemId(orderItem.getItemId())
                .requestedEndDate(orderItem.getRequestedEndDate())
                .build();
    }

    public static OrderItem toEntity(OrderItemDTO orderItemDTO) {
        return OrderItem.builder()
                .itemId(orderItemDTO.getItemId())
                .requestedEndDate(orderItemDTO.getRequestedEndDate())
                .build();
    }

    public static List<OrderItemDTO> toDTO(List<OrderItem> orderItem) {
        if(orderItem == null) return Collections.emptyList();
        return orderItem
                .stream()
                .map(CartDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<OrderItem> toEntity(List<OrderItemDTO> orderItemDTO) {
        if(orderItemDTO == null) return Collections.emptyList();
        return orderItemDTO
                .stream()
                .map(CartDTOMapper::toEntity)
                .collect(Collectors.toList());
    }

}
