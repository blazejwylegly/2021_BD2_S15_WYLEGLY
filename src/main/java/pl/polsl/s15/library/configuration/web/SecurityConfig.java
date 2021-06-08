package pl.polsl.s15.library.configuration.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            "**/swagger-resources/**",
            "**/swagger-ui.html",
            "**/v2/api-docs",
            "/webjars/**",
            "/h2-console/**"
    };

    private UserDetailsService userDetailsService;
    private WebMvcConfigurer globalCorsConfigurer;
    private JwtRequestFilter tokenFilter;
    private FilterChainExceptionHandler filterChainExceptionHandler;

    @Autowired
    public SecurityConfig(@Qualifier("userService") UserDetailsService userDetailsService,
                          @Qualifier("corsConfigurer") WebMvcConfigurer globalCorsConfigurer,
                          JwtRequestFilter tokenFilter,
                          FilterChainExceptionHandler filterChainExceptionHandler) {
        this.userDetailsService = userDetailsService;
        this.globalCorsConfigurer = globalCorsConfigurer;
        this.tokenFilter = tokenFilter;
        this.filterChainExceptionHandler = filterChainExceptionHandler;
    }

    /*
     * In order to create custom AuthenticationManager globally as a bean,
     * we must expose it explicitly, so it uses our AuthenticationManagerBuilder
     * from configure(AuthenticationManagerBuilder auth) method
     * */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
     * This sets up AuthenticationManagerBuilder LOCALLY. Since it's protected,
     * scope of that method applies only to this WebSecurityConfigurerAdapter instance
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureHttpStatelessSessionManagement(http);
        configureFilterChain(http);
        configureCorsAndCsrf(http);

        configureEndpointPermissions(http);
    }

    private void configureCorsAndCsrf(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
    }

    private void configureFilterChain(HttpSecurity http) {
        http
                .addFilterBefore(
                        tokenFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterBefore(
                        filterChainExceptionHandler,
                        LogoutFilter.class
                );
    }


    private void configureHttpStatelessSessionManagement(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
    }

    private void configureEndpointPermissions(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/api/public/**").permitAll()
                .and();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
