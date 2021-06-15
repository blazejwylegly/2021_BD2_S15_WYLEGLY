package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.domain.ordering.Cart;

import java.util.Optional;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long> {
}
