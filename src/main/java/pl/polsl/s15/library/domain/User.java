package pl.polsl.s15.library.domain;

import pl.polsl.s15.library.domain.auth.AccountCredential;
import pl.polsl.s15.library.domain.strategy.AutoGeneratedId;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @EmbeddedId
    private AutoGeneratedId id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private AccountCredential credentials;

}
