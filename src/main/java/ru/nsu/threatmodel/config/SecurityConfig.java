package ru.nsu.threatmodel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> {
                    auth.antMatchers("/api/login/**", "/api/token/refresh/**", "/abbreviation/**").permitAll();
                    auth.antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
                    auth.antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}