package com.nexusnova.lifetravelapi.app.shared.security;

import com.nexusnova.lifetravelapi.app.iam.outbound.services.FirebaseAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public FirebaseAuthenticationFilter firebaseAuthenticationFilter(Environment environment) {
        return new FirebaseAuthenticationFilter(environment);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, Environment environment) throws Exception {
        http.addFilterBefore(firebaseAuthenticationFilter(environment), BasicAuthenticationFilter.class);
        http.csrf((AbstractHttpConfigurer::disable)).cors(AbstractHttpConfigurer::disable);
        return http.build();
    }
}