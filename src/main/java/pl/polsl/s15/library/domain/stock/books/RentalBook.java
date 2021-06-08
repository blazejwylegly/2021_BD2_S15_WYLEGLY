package pl.polsl.s15.library.domain.stock.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental_books")
public class RentalBook extends Book {

    @Column(unique = true, name = "serial_number")
    private Long serialNumber;

    @Column(name = "is_occupied")
    private Boolean isOccupied;

    public RentalBook(BookDetails details, String desc, long serialNumber)
    {
        super(details,desc);
        this.serialNumber = serialNumber;
        this.isOccupied = false;
    }

    public void SetDetailsIfChanged(BookDetails details)
    {
        if(!getDetails().equals(details))
            setDetails(details);
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
