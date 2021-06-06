package pl.polsl.s15.library.dtos.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryArticleInfoDTO {
    private Long articleDetailId;
    private Long deliveryArticleId;
    private String name;
    private String author;
    private String publisher;
    private LocalDate publicationDate;
    private Integer quantity;
}
