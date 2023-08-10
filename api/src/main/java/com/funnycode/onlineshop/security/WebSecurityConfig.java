package com.funnycode.onlineshop.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.funnycode.onlineshop.util.Constant.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/img/**").permitAll()
                .antMatchers(String.format("%s/login", API_VERSION)).permitAll()
                .antMatchers(HttpMethod.POST, String.format("%s/accounts", API_VERSION)).permitAll()
                .antMatchers(HttpMethod.GET, String.format("%s/products/**", API_VERSION)).permitAll()
                .antMatchers(HttpMethod.GET, String.format("%s/categories/**", API_VERSION)).permitAll()
                .antMatchers(HttpMethod.GET, String.format("%s/brands/**", API_VERSION)).permitAll()
                .antMatchers(HttpMethod.GET, String.format("%s/colors/**", API_VERSION)).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
