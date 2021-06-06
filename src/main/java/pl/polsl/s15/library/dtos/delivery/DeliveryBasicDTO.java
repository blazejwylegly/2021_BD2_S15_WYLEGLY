package pl.polsl.s15.library.dtos.delivery;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DeliveryBasicDTO {
    private Long id;
    private Long quantity;
    private LocalDateTime deliveryRequestDate;
    private LocalDateTime expectedDeliveryDate;

    public DeliveryBasicDTO(Long id, List<DeliveryArticle> deliveryArticles, LocalDateTime deliveryRequestDate, LocalDateTime expectedDeliveryDate) {
        this.id = id;
        this.quantity = deliveryArticles.stream().count();
        this.deliveryRequestDate = deliveryRequestDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
