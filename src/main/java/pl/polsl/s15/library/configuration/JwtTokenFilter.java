package pl.polsl.s15.library.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.polsl.s15.library.security.jwt.JwtUtility;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
    private final UserDetailsService userService;

    @Autowired
    public JwtTokenFilter(JwtUtility jwtUtility, UserDetailsService userService) {
        this.jwtUtility = jwtUtility;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        authenticateUserWithJwtAuth(request);
        filterChain.doFilter(request, response);
    }

    private void authenticateUserWithJwtAuth(HttpServletRequest request) {
        retrieveAuthentication(request)
                .ifPresent(this::updateSecurityContextWithAuthenticatedUser);
    }

    private Optional<Authentication> retrieveAuthentication(HttpServletRequest request) {
        Optional<String> token = extractToken(request);
        return token.map(t -> buildAuthenticationWithJwt(t, request));
    }

    private Authentication buildAuthenticationWithJwt(String token, HttpServletRequest request) {
        UserDetails userDetails = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
        UsernamePasswordAuthenticationToken authentication = mapUserToAuthentication(userDetails);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    private UsernamePasswordAuthenticationToken mapUserToAuthentication(UserDetails user) {
        return new UsernamePasswordAuthenticationToken(user, null);
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        return this.extractTokenFromHeaderIfExists(
                request.getHeader(HttpHeaders.AUTHORIZATION)
        );
    }

    private Optional<String> extractTokenFromHeaderIfExists(String authHeader) {
        return Optional.ofNullable(authHeader)
        .filter(header -> header.startsWith("Bearer "))
        .map(header -> header.split(" ")[1].trim());
    }

    private void updateSecurityContextWithAuthenticatedUser(Authentication auth) {
        SecurityContextHolder.getContext().setAuthentication(auth);

    }
}