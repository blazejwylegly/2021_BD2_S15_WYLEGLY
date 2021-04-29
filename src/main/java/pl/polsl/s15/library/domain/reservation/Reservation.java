package pl.polsl.s15.library.domain.reservation;

import pl.polsl.s15.library.domain.orderable.Orderable;
import pl.polsl.s15.library.domain.strategy.IdStrategyClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity(name = "Reservations")
public class Reservation extends IdStrategyClass {

    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "orderable_id", referencedColumnName = "id",nullable = false)
    private Orderable orderable;

}
