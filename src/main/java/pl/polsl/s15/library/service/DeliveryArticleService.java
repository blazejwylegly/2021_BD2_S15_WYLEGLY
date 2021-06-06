package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.commons.exceptions.delivery.DeliveryCantBeCreatedException;
import pl.polsl.s15.library.domain.deliveries.Delivery;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;
import pl.polsl.s15.library.dtos.delivery.DeliveryArticleDTO;
import pl.polsl.s15.library.repository.BookDetailsRepository;
import pl.polsl.s15.library.repository.DeliveryArticleRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DeliveryArticleService {
    private final DeliveryArticleRepository deliveryArticleRepository;
    private final BookDetailsRepository bookDetailsRepository;

    public DeliveryArticle createDeliveryArticle(DeliveryArticleDTO articleDTO, Delivery delivery){
        final DeliveryArticle deliveryArticle = new DeliveryArticle();
        deliveryArticle.setDelivery(delivery);
        deliveryArticle.setArticleDetails(bookDetailsRepository.findById(articleDTO.getArticleDetailId())
                .orElseThrow(()->new DeliveryCantBeCreatedException("Delivery cannot be created because article details with id: " + articleDTO.getArticleDetailId() + "doesnt exists")));
        deliveryArticle.setAmount(articleDTO.getAmount());
        return deliveryArticle;
    }

    public List<DeliveryArticle> save(List<DeliveryArticle> deliveryArticles){
        return deliveryArticleRepository.saveAll(deliveryArticles);
    }
}
