package pl.polsl.s15.library.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.commons.exceptions.InvalidJwtException;

import javax.xml.bind.DatatypeConverter;

@Component
@Slf4j
public class JwtParser {

    private static String JWT_SECRET;

    @Autowired
    public JwtParser(@Value("${app.auth.jwt.secret}") String jwtSecret) {
        this.JWT_SECRET = jwtSecret;
    }

    public Claims parseClaimsFromJwt(String jwt) throws InvalidJwtException {
        try {
            return tryToParseClaims(jwt);
        } catch (JwtException e) {
            throw new InvalidJwtException("JWT Validation Error: " + e.getMessage(), e);
        }
    }

    public Claims tryToParseClaims(String jwt) throws JwtException {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET))
                .parseClaimsJws(jwt).getBody();
    }
}
