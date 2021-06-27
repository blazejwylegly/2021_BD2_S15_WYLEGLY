package pl.polsl.s15.library.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "employees")
@NoArgsConstructor
public class Employee extends User {
    public Employee(User user) {
        super(user);
    }
}
