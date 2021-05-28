package pl.polsl.s15.library.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.domain.user.User;

import java.util.Date;

@Component
public class JwtUtility {

    private JwtBuilder builder;
    private JwtParser parser;

    @Autowired
    public JwtUtility(JwtBuilder builder, JwtParser parser) {
        this.builder = builder;
        this.parser = parser;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = parser.tryToParseClaims(token);
        return claims.getSubject();
    }

    public String generateAccessToken(User user, Date issuedAt) {
        return builder.generateToken(user, issuedAt);
    }

    public String generateAccessToken(User user) {
        return generateAccessToken(user, new Date());
    }
}
