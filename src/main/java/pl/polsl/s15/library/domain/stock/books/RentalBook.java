package pl.polsl.s15.library.domain.stock.books;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.reservations.Reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rental_books")
public class RentalBook extends Book {

    @Column(unique = true, name = "serial_number")
    private Long serialNumber;

    @Column(name = "is_occupied")
    private Boolean isOccupied;

    @OneToMany(mappedBy = "rentalBook")
    private List<Reservation> reservations = new ArrayList<>();
}
