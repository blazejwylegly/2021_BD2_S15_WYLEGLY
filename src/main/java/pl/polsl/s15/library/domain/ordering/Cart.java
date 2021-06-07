package pl.polsl.s15.library.domain.ordering;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.dtos.ordering.CartDTO;
import pl.polsl.s15.library.dtos.ordering.OrderItemDTO;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "cart")
    private Client client;

    public static Cart ofDTO(CartDTO cartDTO) {
        List<OrderItem> orderItems = mapOrderItemDTOsToEntities(cartDTO.getOrderItems());

        return Cart.builder()
                .id(cartDTO.getId())
                .orderItems
    }

    private static List<OrderItem> mapOrderItemDTOsToEntities(List<OrderItemDTO> orderItems) {
        return orderItems.stream()
                .map(OrderItem::ofDTO)
                .collect(Collectors.toList());
    }
}

