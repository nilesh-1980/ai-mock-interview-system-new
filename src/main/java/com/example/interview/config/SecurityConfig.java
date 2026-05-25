package com.example.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // PASSWORD ENCODER
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // SECURITY
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            // CSRF DISABLE
            .csrf(csrf -> csrf.disable())

            // AUTHORIZATION
            .authorizeHttpRequests(auth -> auth

                    // PUBLIC PAGES
                    .requestMatchers(
                            "/register",
                            "/saveUser",
                            "/login",
                            "/css/**"
                    ).permitAll()

                    // ADMIN ACCESS
                    .requestMatchers("/admin/**")
                    .hasRole("ADMIN")

                    // USER ACCESS
                    .requestMatchers("/user/**")
                    .hasRole("USER")

                    // DASHBOARD LOGIN REQUIRED
                    .requestMatchers("/dashboard")
                    .authenticated()

                    // EVERYTHING ELSE
                    .anyRequest()
                    .authenticated()
            )

            // LOGIN
            .formLogin(form -> form

                    .loginPage("/login")

                    .loginProcessingUrl("/login")

                    // IMPORTANT
                    .defaultSuccessUrl("/dashboard", true)

                    .permitAll()
            )

            // LOGOUT
            .logout(logout -> logout

                    .logoutSuccessUrl("/login?logout")

                    .permitAll()
            );

        return http.build();
    }

    // AUTH MANAGER
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }
}