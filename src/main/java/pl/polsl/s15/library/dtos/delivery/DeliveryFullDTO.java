package pl.polsl.s15.library.dtos.delivery;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryFullDTO {
    private Long id;
    private LocalDateTime deliveryRequestDate;
    private LocalDateTime expectedDeliveryDate;
    private List<DeliveryArticleInfoDTO> deliveryArticles;
}
