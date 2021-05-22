package pl.polsl.s15.library.security.jwt;

import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.context.SpringBootTest;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.security.BaseSecurityTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtBuilderTest extends BaseSecurityTest {

    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;

    @BeforeEach
    public void setup() {
        this.jwtBuilder = new JwtBuilder(JWT_SECRET, EXP_MS);
        this.jwtParser = new JwtParser(JWT_SECRET);
    }

    @Test
    public void shouldBuildTokenWithBasicClaims() {
        // GIVEN
        Date issuedAt = new Date();
        Date expirationDate = expirationDate(issuedAt);
        User user = user("user");

        // WHEN
        String token = jwtBuilder.generateToken(user, issuedAt);
        Claims claims = jwtParser.parseClaimsFromJwt(token);

        // THEN
        assertThat(claims.getSubject()).isEqualTo("user");
        assertThat(claims.getIssuedAt()).isEqualTo(issuedAt);
        assertThat(claims.getExpiration()).isEqualTo(expirationDate);
    }

    @Test
    public void shouldBuildTokenWithRolesAndAuthorities() {
        User user = user("user");
        String token = jwtBuilder.generateToken(user);

        Claims claims = jwtParser.parseClaimsFromJwt(token);
        assertThat(claims.getSubject()).isEqualTo("user");
        assertThat(claims.getIssuedAt()).isBefore(new Date());
    }


}