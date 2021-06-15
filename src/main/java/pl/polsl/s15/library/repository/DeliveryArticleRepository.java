package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.s15.library.domain.deliveries.DeliveryArticle;

import java.util.Optional;

@Repository
public interface DeliveryArticleRepository extends JpaRepository<DeliveryArticle, Long> {
    Optional<DeliveryArticle> findByArticleDetailsIdAndDeliveryId(Long id, Long deliveryId);
}
