package pl.polsl.s15.library.domain.order;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.orderItem.OrderItem;
import pl.polsl.s15.library.domain.strategy.IdStrategyClass;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Orders")
public class Order extends IdStrategyClass {

    private Date dateOfOrderSubmission;

    private Long orderState;

    private Integer isPrepared;

    private Long clientId;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
}
