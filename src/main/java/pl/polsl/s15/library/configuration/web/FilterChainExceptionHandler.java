package pl.polsl.s15.library.configuration.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class FilterChainExceptionHandler extends OncePerRequestFilter {

    private HandlerExceptionResolver exceptionResolver;

    public FilterChainExceptionHandler(
            @Autowired @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception exception) {
            log.warn("Error occurred during request filtering!\n Details {}", exception.getMessage(), exception);
            exceptionResolver.resolveException(
                    httpServletRequest,
                    httpServletResponse,
                    null,
                    exception
            );
        }
    }
}
