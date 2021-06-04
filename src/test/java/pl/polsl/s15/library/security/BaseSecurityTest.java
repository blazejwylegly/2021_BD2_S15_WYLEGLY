package pl.polsl.s15.library.security;

import org.springframework.test.context.ActiveProfiles;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.AccountCredentials;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;
import pl.polsl.s15.library.domain.user.account.roles.Role;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@ActiveProfiles("dev")
public class BaseSecurityTest {

    protected static final String JWT_SECRET = "secret";
    protected static final int EXP_MS = 3600000;
    protected static final String DEFAULT_CREDENTIAL_VALUE = "default";

    protected User user(String username) {
        return user(username, Collections.emptySet());
    }

    protected User user(String username, Set<Role> roles) {
        User user = new User();
        user.setCredentials(userCredentials(username));
        user.setPermissions(userPermissions(roles));
        return user;
    }

    protected AccountCredentials userCredentials(String username) {
        return AccountCredentials.builder()
                .username(username)
                .build();
    }

    protected AccountPermissions userPermissions(Set<Role> roles) {
        return AccountPermissions.builder()
                .roles(roles)
                .build();
    }

    protected Date expirationDate(Date issuedAt) {
        return new Date(issuedAt.getTime() + EXP_MS);
    }
}
