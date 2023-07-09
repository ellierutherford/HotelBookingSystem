package com.marriott.booking;

import com.marriott.booking.model.StarwoodUserDetails;
import com.marriott.booking.services.StarwoodUserDetailsSvc;
import jakarta.servlet.DispatcherType;
import org.hibernate.metamodel.mapping.ModelPart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {


    private final StarwoodUserDetailsSvc starwoodUserDetailsSvc;

    public WebSecurityConfig(StarwoodUserDetailsSvc userDetailsSvc){
        this.starwoodUserDetailsSvc = userDetailsSvc;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/**").permitAll()
                            // nb this line is needed for spring security to work with jsp
                            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                            .anyRequest().authenticated()
                )
                .userDetailsService(starwoodUserDetailsSvc)
                .formLogin(withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}