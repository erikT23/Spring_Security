package mx.edu.utez.sda.u2p1.u2p1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {


    @Autowired
    private DataSource dataSource;

    public void globalConfigure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // configura paths públicos
        http.authorizeRequests((requests) -> {
            requests.requestMatchers("/", "/home").permitAll();
            requests.anyRequest().authenticated();
        });

        // configura página de login
        http.formLogin((login) -> {
            login.loginPage("/login").permitAll().defaultSuccessUrl("/home", true);
        });

        http.logout((logout) -> {
            logout.permitAll();
        });
        return http.build();
    }

   /* @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("rodrigo")
                .password("root")
                .roles("ADMIN")
                .build();
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("alex")
                .password("root")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/
}
