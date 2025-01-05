package com.airbnb2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;



    @Bean
    @Configuration
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                .antMatchers("/api/v1/addUser", "/api/v1/login") // Use antMatchers for URL patterns
                .permitAll()  // Ensure these URLs are accessible without authentication
                .anyRequest().authenticated(); // Ensure other requests require authentication
        return http.build();
    }
}

