package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    void deleteByRoleType(RoleType roleType);
    Optional<Role> findRoleByRoleType(RoleType roleType);
}
