package pl.polsl.s15.library.domain.user.account.roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authorities")
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "authority")
    private String authority;

    @ManyToMany
    @JoinTable(
            name = "permissions_authorities",
            joinColumns = @JoinColumn(name ="authority_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<AccountPermissions> permissions;

}
