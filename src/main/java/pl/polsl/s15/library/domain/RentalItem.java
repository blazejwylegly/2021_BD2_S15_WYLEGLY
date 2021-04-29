package pl.polsl.s15.library.domain;

import lombok.Getter;
import pl.polsl.s15.library.domain.orderable.Item;
import pl.polsl.s15.library.domain.reservation.Reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class RentalItem extends Item {

    @Column(unique = true)
    private Long serialNumber;

    private Boolean isOccupied;

    private Boolean isMarkedForReservation;

    @OneToMany(mappedBy = "rentalItem")
    private List<Reservation> reservations = new ArrayList<>();
}
