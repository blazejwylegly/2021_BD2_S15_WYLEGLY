package pl.polsl.s15.library.domain.stock.books;

import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.s15.library.domain.reservations.Reservation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "rental_books")
public class RentalBook extends Book {

    @Column(unique = true)
    private Long serialNumber;

    private Boolean isOccupied;

    @OneToMany(mappedBy = "rentalBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
    public RentalBook(BookDetails details, String desc, long serialNumber)
    {
        super(details,desc);
        this.serialNumber = serialNumber;
        this.isOccupied = false;
    }
    //is you try occupy occupied book, false is returned to indicate error
    public boolean Occupy()
    {
        if(isOccupied)
            return false;
        this.isOccupied = true;
        return true;
    }
    //if you try to free not occupied book, false is returned to indicate error
    public boolean Free()
    {
        if(!isOccupied)
            return false;
        this.isOccupied = false;
        return true;
    }
}
