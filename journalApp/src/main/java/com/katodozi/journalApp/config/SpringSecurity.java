package com.katodozi.journalApp.config;

import com.katodozi.journalApp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this class as a configuration class for Spring
@EnableWebSecurity // Enables Spring Security for the application
public class SpringSecurity {

    @Autowired // Injects the custom UserDetailsService implementation
    private UserDetailsServiceImpl userDetailsService;

    @Bean // Defines a Bean managed by Spring’s container
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Configures authorization rules for different URL patterns
        return http.authorizeHttpRequests(request -> request
                        // Allows all requests to URLs starting with /public/
                        .requestMatchers("/public/**").permitAll()
                        // Requires authentication for these endpoints
                        .requestMatchers("/journal/**", "/user/**").authenticated()
                        // Allows only users with ADMIN role to access /admin/
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Requires authentication for any other requests
                        .anyRequest().authenticated())
                // Enables basic authentication using username and password in headers
                .httpBasic(Customizer.withDefaults())
                // Disables CSRF protection (useful for APIs)
                .csrf(AbstractHttpConfigurer::disable)
                // Builds and returns the SecurityFilterChain bean
                .build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Sets the custom userDetailsService and password encoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean // Defines BCrypt password encoder bean
    public PasswordEncoder passwordEncoder() {
        // Returns a BCrypt implementation for password hashing
        return new BCryptPasswordEncoder();
    }
}
