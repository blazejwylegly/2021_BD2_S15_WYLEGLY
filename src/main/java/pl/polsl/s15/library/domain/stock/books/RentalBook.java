package pl.polsl.s15.library.domain.stock.books;

import pl.polsl.s15.library.domain.reservations.Reservation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rental_books")
public class RentalBook extends Book {

    @Column(unique = true)
    private Long serialNumber;

    private Boolean isOccupied;

    @OneToMany(mappedBy = "rentalBook")
    private List<Reservation> reservations = new ArrayList<>();
}
