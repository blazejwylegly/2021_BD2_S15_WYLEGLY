package pl.polsl.s15.library.dtos.reservations.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartMetaData {
    private Long cartId;
    private Integer itemsNumber;
}
