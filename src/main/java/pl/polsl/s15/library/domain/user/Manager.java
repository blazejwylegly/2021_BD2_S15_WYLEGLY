package pl.polsl.s15.library.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="managers")
public class Manager extends User {
}
