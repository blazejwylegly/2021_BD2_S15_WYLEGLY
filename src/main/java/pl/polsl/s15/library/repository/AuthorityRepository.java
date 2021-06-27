package pl.polsl.s15.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.s15.library.domain.user.account.roles.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    void deleteByAuthority(String authority);
    boolean existsByAuthority(String authority);
}
