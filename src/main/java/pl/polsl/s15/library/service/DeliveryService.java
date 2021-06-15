package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.commons.exceptions.delivery.DeliveryCantBeCreatedException;
import pl.polsl.s15.library.commons.exceptions.delivery.DeliveryNotFoundException;
import pl.polsl.s15.library.domain.deliveries.Delivery;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;
import pl.polsl.s15.library.domain.deliveries.DeliveryStatus;
import pl.polsl.s15.library.dtos.delivery.*;
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

    @Transactional
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
        } else {
            throw new DeliveryCantBeCreatedException("There is no delivery articles");
        }
    }

    public List<DeliveryBasicDTO> findAll() {
        return deliveryRepository
                .findAll()
                .stream()
                .map(delivery ->
                        new DeliveryBasicDTO(delivery.getId(), delivery.getOrderedItems(), delivery.getDeliveryRequestDate(), delivery.getExpectedDeliveryDate())
                )
                .collect(Collectors.toList());
    }

    private Delivery findDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException("Not found delivery with id: " + id));
    }

    public DeliveryFullDTO findById(Long id) {
        Delivery delivery = findDeliveryById(id);
        List<DeliveryArticleInfoDTO> deliveryArticleInfoDTOS = delivery.getOrderedItems()
                .stream()
                .map(d -> deliveryArticleService.getInfoAboutDeliveryArticle(d))
                .collect(Collectors.toList());
        return new DeliveryFullDTO(delivery.getId(), delivery.getDeliveryRequestDate(), delivery.getExpectedDeliveryDate(), deliveryArticleInfoDTOS);
    }

//    @Transactional
//    public void update(DeliveryArticleDTO articleDTO, Long deliveryId) {
//        Delivery delivery = findDeliveryById(deliveryId);
//        if (!deliveryArticleService.update(articleDTO.getArticleDetailId(), articleDTO.getAmount(), delivery.getId())) {
//            DeliveryArticle deliveryArticle = deliveryArticleService.createDeliveryArticle(articleDTO, delivery);
//            delivery.getOrderedItems().add(deliveryArticle);
//            deliveryArticleService.save(deliveryArticle);
//        }
//    }
}
