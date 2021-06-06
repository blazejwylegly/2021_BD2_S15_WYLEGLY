package pl.polsl.s15.library.dtos.delivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryArticleDTO {
    private int amount;
    private Long articleDetailId;
}
