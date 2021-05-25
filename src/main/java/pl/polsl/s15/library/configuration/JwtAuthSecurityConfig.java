package pl.polsl.s15.library.configuration;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@Slf4j
public class JwtAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private WebMvcConfigurer globalCorsConfigurer;

    public JwtAuthSecurityConfig(UserDetailsService userDetailsService,
                                 @Qualifier("corsConfigurer") WebMvcConfigurer globalCorsConfigurer) {
        this.userDetailsService = userDetailsService;
        this.globalCorsConfigurer = globalCorsConfigurer;
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
        log.warn("USING AUTH BUILDER");
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll();
//        configureHttpStatelessSessionManagement(http);
//        configureEndpointPermissions(http);
//        configureCorsAndCsrf(http);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }

    private void configureCorsAndCsrf(HttpSecurity httpSecurity) throws Exception {
        httpSecurity = httpSecurity.cors().and().csrf().disable();
    }

    private void configureHttpStatelessSessionManagement(HttpSecurity httpSecurity) throws Exception {
        httpSecurity = httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
    }

    private void configureEndpointPermissions(HttpSecurity httpSecurity) throws Exception {
        httpSecurity = httpSecurity.authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/console/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .and();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
