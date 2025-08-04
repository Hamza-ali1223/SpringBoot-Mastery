package com.springmastery.Spring_Mastery.configuration;

import com.springmastery.Spring_Mastery.services.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class config {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CustomUserDetails customUserDetails;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //noinspection removal
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/user").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()  .requestMatchers("/test-admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf().disable()
                .httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoauth = new DaoAuthenticationProvider(customUserDetails);
        daoauth.setPasswordEncoder(passwordEncoder());
        return daoauth;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
