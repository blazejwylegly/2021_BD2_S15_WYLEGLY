package pl.polsl.s15.library.dtos.delivery;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DeliveryDTO {
    private List<DeliveryArticleDTO> deliveryArticles;
    private LocalDateTime expectedDeliveryDate;
}
