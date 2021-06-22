package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.commons.exceptions.delivery.DeliveryCantBeCreatedException;
import pl.polsl.s15.library.commons.exceptions.delivery.DeliveryNotFoundException;
import pl.polsl.s15.library.domain.deliveries.Delivery;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;
import pl.polsl.s15.library.domain.stock.books.BookDetails;
import pl.polsl.s15.library.dtos.delivery.DeliveryArticleDTO;
import pl.polsl.s15.library.dtos.delivery.DeliveryArticleInfoDTO;
import pl.polsl.s15.library.repository.BookDetailsRepository;
import pl.polsl.s15.library.repository.DeliveryArticleRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DeliveryArticleService {
    private final DeliveryArticleRepository deliveryArticleRepository;
    private final BookDetailsRepository bookDetailsRepository;

    public DeliveryArticle createDeliveryArticle(DeliveryArticleDTO articleDTO, Delivery delivery){
        final DeliveryArticle deliveryArticle = new DeliveryArticle();
        deliveryArticle.setArticleDetails(bookDetailsRepository.findById(articleDTO.getArticleDetailId())
                .orElseThrow(() -> new DeliveryCantBeCreatedException("Delivery cannot be created because article details with id: " + articleDTO.getArticleDetailId() + "doesnt exists")));
        deliveryArticle.setAmount(articleDTO.getAmount());
        return deliveryArticle;
    }

    @Transactional
    public List<DeliveryArticle> save(List<DeliveryArticle> deliveryArticles) {
        return deliveryArticleRepository.saveAll(deliveryArticles);
    }

    @Transactional
    public DeliveryArticle save(DeliveryArticle deliveryArticle) {
        return deliveryArticleRepository.save(deliveryArticle);
    }

    public DeliveryArticleInfoDTO getInfoAboutDeliveryArticle(DeliveryArticle deliveryArticle) {
        BookDetails bookDetails = bookDetailsRepository.findById(deliveryArticle.getArticleDetails().getId())
                .orElseThrow(() -> new DeliveryNotFoundException(""));
        return new DeliveryArticleInfoDTO(bookDetails.getId(), deliveryArticle.getId(), bookDetails.getName(), bookDetails.getAuthor(), bookDetails.getPublisher(), bookDetails.getPublicationDate(), deliveryArticle.getAmount());

    }

//    @Transactional
//    public boolean update(Long id, Integer amount, Long deliveryId) {
//        Optional<DeliveryArticle> deliveryArticle = deliveryArticleRepository.findByArticleDetailsIdAndDeliveryId(id, deliveryId);
//        if (deliveryArticle.isPresent()) {
//            deliveryArticle.get().setAmount(deliveryArticle.get().getAmount() + amount);
//            if (deliveryArticle.get().getAmount() <= 0) {
//                deliveryArticleRepository.deleteById(deliveryArticle.get().getId());
//            } else
//                save(deliveryArticle.get());
//
//            return true;
//        } else {
//            return false;
//        }
//    }
}
