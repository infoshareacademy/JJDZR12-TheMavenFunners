package com.isa.tasktrackerwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/registration", "/login", "/logUser", "/registerUser", "/logout").permitAll()
            .requestMatchers("/css/**", "/img/**").permitAll()
            .anyRequest().authenticated())
        .formLogin(form -> form
            .loginPage("/login")
            .usernameParameter("login")
            .successForwardUrl("/logUser")
            .failureUrl("/login?error=true"))
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/"));
    return httpSecurity.build();
  }
}