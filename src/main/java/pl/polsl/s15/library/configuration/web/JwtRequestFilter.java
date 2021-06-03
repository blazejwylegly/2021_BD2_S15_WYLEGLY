package pl.polsl.s15.library.configuration.web;

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
import pl.polsl.s15.library.utility.jwt.JwtUtility;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
    private final UserDetailsService userService;

    @Autowired
    public JwtRequestFilter(JwtUtility jwtUtility, UserDetailsService userService) {
        this.jwtUtility = jwtUtility;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        getToken(request)
                .map(token -> authenticateUser(token, request))
                .ifPresent(this::updateSecurityContextWithAuthenticatedUser);
        filterChain.doFilter(request, response);
    }

    private Authentication authenticateUser(String token, HttpServletRequest request) {
        final UserDetails userDetails = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
        var authentication = mapUserToAuth(userDetails);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    UsernamePasswordAuthenticationToken mapUserToAuth(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), null, null
        );
    }

    Optional<String> getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(header -> header.startsWith("Bearer "))
                .map(header -> header.split(" ")[1].trim());
    }

    private void updateSecurityContextWithAuthenticatedUser(Authentication auth) {
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}