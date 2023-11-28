package com.bitsbids.bitsbids.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // tells spring to look in this class for bean declaration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // This is used to permit all requests without checking for authentication
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth ->
            auth.anyRequest().permitAll());
        return http.build();
    }

}
