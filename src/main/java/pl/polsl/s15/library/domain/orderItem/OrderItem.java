package pl.polsl.s15.library.domain.orderItem;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.order.Order;
import pl.polsl.s15.library.domain.orderable.Orderable;
import pl.polsl.s15.library.domain.strategy.IdStrategyClass;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter
@Setter
@Entity(name = "order_items")
public class OrderItem extends IdStrategyClass {

    private Integer isOrderItemReady;

    @ManyToOne
    @JoinColumn(name = "orderable_id", referencedColumnName = "id")
    private Orderable orderable;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
