/*package com.mycompany.platforme_telemedcine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and() // Enable CORS
                .csrf().disable() // Disable CSRF
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // Allow ALL requests without authentication
                );

        return http.build();
    }
}
*/