package pl.polsl.s15.library.security.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.domain.user.account.AccountPermissions;

import java.util.*;

@Component
@Slf4j
public class JwtBuilder {

    private String jwtSecret;
    private int jwtExpirationMs;

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    @Autowired
    public JwtBuilder(
            @Value("${app.auth.jwt.secret}") String jwtSecret,
            @Value("${app.auth.jwt.expiration.ms}") int jwtExpirationMs) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String generateToken(Authentication authenticationToken) {
        User authenticatedUser = (User) authenticationToken.getPrincipal();
        log.debug("Generating token for {}", authenticatedUser.getUsername());
        return generateToken(authenticatedUser);
    }

    public String generateToken(User user) {
        return generateToken(user, new Date());
    }


    public String generateToken(User user, Date issuedAt) {
        return Jwts.builder()
                .setHeaderParam(Header.CONTENT_TYPE, Header.JWT_TYPE)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate(issuedAt))
                .addClaims(preparePermissionClaims(user.getPermissions()))
                .signWith(SIGNATURE_ALGORITHM, jwtSecret)
                .compact();
    }

    private Map<String, Object> preparePermissionClaims(AccountPermissions permissions) {
        Map<String, Object> claims = new HashMap<>();
        if (permissions == null) {
            log.warn("No permissions found inside JWT!");
        } else {
            claims.put("roles", permissions.getRoles());
            claims.put("authorities", Collections.emptySet());
        }
        return claims;
    }

    private Date expirationDate(Date issuedAt) {
        return new Date(issuedAt.getTime() + jwtExpirationMs);
    }
}
