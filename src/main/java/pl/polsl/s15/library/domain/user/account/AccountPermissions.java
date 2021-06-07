package pl.polsl.s15.library.domain.user.account;

import lombok.*;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.roles.Authority;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AuthorityDTO;
import pl.polsl.s15.library.dtos.users.permissions.RoleDTO;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
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
            joinColumns = @JoinColumn(name ="account_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

    @ManyToMany
    @JoinTable(
            name = "permissions_roles",
            joinColumns = @JoinColumn(name ="account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public static AccountPermissions of(AccountPermissionsDTO accountPermissionsDTO) {
        Set<Authority> authorities  = mapDTOSToAuthorities(accountPermissionsDTO.getAuthorities());
        Set<Role> roles = mapDTOSToRoles(accountPermissionsDTO.getRoles());

        return AccountPermissions.builder()
                .id(accountPermissionsDTO.getId())
                .authorities(authorities)
                .roles(roles)
                .build();
    }

    public static Set<Role> mapDTOSToRoles(Set<RoleDTO> roles) {
        return roles.stream()
                .map(Role::ofDTO)
                .collect(Collectors.toSet());
    }

    public static Set<Authority> mapDTOSToAuthorities(Set<AuthorityDTO> authorities) {
        return authorities.stream()
                .map(Authority::ofDTO)
                .collect(Collectors.toSet());
    }
}
