package pl.polsl.s15.library.domain.reservations;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.domain.user.Client;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

    @Column(nullable = false, name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private RentalBook rentalBook;
}
