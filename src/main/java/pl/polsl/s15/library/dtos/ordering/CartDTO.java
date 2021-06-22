package pl.polsl.s15.library.dtos.ordering;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CartDTO {
    private Long id;
    private List<OrderItemDTO> orderItems;
}
