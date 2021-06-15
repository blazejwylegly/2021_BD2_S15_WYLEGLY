package pl.polsl.s15.library.domain.user.account.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;

    @Override
    public String getAuthority() {
        return name.getValue();
    }

}
