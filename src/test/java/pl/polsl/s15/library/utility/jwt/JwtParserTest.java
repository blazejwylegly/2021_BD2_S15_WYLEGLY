package pl.polsl.s15.library.utility.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.s15.library.security.BaseSecurityTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
class JwtParserTest extends BaseSecurityTest {

    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;

    @BeforeEach
    public void setup() {
        this.jwtBuilder = new JwtBuilder(JWT_SECRET, EXP_MS);
        this.jwtParser = new JwtParser(JWT_SECRET);
    }

    @Test
    public void shouldParseSubjectFromToken() throws IOException {
//        String token = getTokenFromFile(SUBJECT_ONLY_JWT);
//        Claims claims = jwtParser.parseClaimsFromJwt(token);
//        assertThat(claims.getSubject()).isNotNull();
        //asser
    }
}