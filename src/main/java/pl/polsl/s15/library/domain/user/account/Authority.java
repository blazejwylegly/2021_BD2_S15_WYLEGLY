package pl.polsl.s15.library.domain.user.account;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "permissions_authorities",
            joinColumns = @JoinColumn(name ="authority_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<AccountPermissions> accounts;
}
