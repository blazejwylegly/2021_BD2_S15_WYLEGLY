package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.commons.exceptions.delivery.DeliveryCantBeCreatedException;
import pl.polsl.s15.library.domain.deliveries.Delivery;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;
import pl.polsl.s15.library.domain.deliveries.DeliveryStatus;
import pl.polsl.s15.library.domain.stock.ArticleDetails;
import pl.polsl.s15.library.dtos.delivery.DeliveryArticleDTO;
import pl.polsl.s15.library.dtos.delivery.DeliveryDTO;
import pl.polsl.s15.library.repository.BookDetailsRepository;
import pl.polsl.s15.library.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryArticleService deliveryArticleService;

    public void create(DeliveryDTO dto) {
        if(!dto.getDeliveryArticles().isEmpty()){
            final Delivery delivery = new Delivery();
        List<DeliveryArticle> deliveryArticleList =  dto.getDeliveryArticles().stream()
                .map(a -> deliveryArticleService.createDeliveryArticle(a,delivery))
                .collect(Collectors.toList());
        delivery.setDeliveryStatus(DeliveryStatus.SUBMITTED);
        delivery.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());
        delivery.setOrderedItems(deliveryArticleList);
        delivery.setDeliveryRequestDate(LocalDateTime.now());
        deliveryArticleService.save(deliveryArticleList);
        deliveryRepository.save(delivery);
        }
        else{
            throw new DeliveryCantBeCreatedException("There is no delivery articles");
        }
    }
}
