package pl.polsl.s15.library.domain.user.account.roles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;

import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;

    @ManyToMany
    @JoinTable(
            name = "permissions_roles",
            joinColumns = @JoinColumn(name ="role_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<AccountPermissions> accounts;

    @Override
    public String getAuthority() {
        return name.getValue();
    }
}
