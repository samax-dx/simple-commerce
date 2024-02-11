package com.samax.simpleCommerce.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.samax.simpleCommerce.session.service.SessionUserCheckFilter;


@Configuration
public class SpringSecurityConfig {

    @Bean("sessionFilterChain")
    public SecurityFilterChain filterChain(HttpSecurity http, SessionUserCheckFilter sessionUserCheckFilter) throws Exception {
        return http.addFilterAfter(sessionUserCheckFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

}
