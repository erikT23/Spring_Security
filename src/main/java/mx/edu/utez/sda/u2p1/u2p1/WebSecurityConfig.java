package mx.edu.utez.sda.u2p1.u2p1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Autowired
    private DataSource dataSource;

    public void globalConfigure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // configura paths públicos
        http.authorizeRequests(
                (requests) -> {
                    requests.requestMatchers("/", "/home").permitAll();
                    requests.anyRequest().authenticated();
                }
        );

        // configura página de login
        http.formLogin(
                (login) -> {
                    login.loginPage("/login").permitAll();
                }
        );

        http.logout(
                (logout) -> {
                    logout.permitAll();
                }
        );
        return http.build();
    }
}
