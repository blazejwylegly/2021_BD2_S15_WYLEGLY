package pl.polsl.s15.library.domain.user.account;

import lombok.*;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.roles.Authority;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "permissions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "permissions")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "permissions_authorities",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

    @ManyToMany
    @JoinTable(
            name = "permissions_roles",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public void overrideRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addNewRoleIfNotPresent(Role role) {
        if (!roles.contains(role)) {
            this.roles.add(role);
        }
    }

    public void deleteRoleIfPresent(Role roleToBeDeleted) {
        RoleType roleType = roleToBeDeleted.getRoleType();
        roles.stream()
                .filter(role -> role.getRoleType().equals(roleType))
                .findFirst()
                .ifPresent(roles::remove);
    }
}
