package pl.polsl.s15.library.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee extends User {
}
