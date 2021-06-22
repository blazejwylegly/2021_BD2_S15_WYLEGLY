package pl.polsl.s15.library.domain.reservations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.s15.library.commons.enums.ReservationStatus;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.domain.user.Client;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "start_time")
    private LocalDate startTime;

    @Column(nullable = false, name = "end_time")
    private LocalDate endTime;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private RentalBook rentalBook;

    @Column
    private Boolean returned = false;

    private ReservationStatus status;

    public Reservation(RentalBook rentalBook, LocalDate end_time) {
        this.startTime = LocalDate.now();
        this.rentalBook = rentalBook;
        this.endTime = end_time;
        this.status = ReservationStatus.PENDING;
    }

    public String getReport()
    {
        return "ID: " + id.toString() + " From: " + startTime.toString() + " To: " + endTime.toString() + " Status: " + status.toString();
    }
}
