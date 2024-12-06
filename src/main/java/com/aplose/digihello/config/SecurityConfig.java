package com.aplose.digihello.config;

import com.aplose.digihello.mapper.UserMapper;
import com.aplose.digihello.repository.UserAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
        return username -> UserMapper.toUserDetails(userAccountRepository.findByUsername(username));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/", "login").permitAll()
                .requestMatchers("logout").authenticated()
                .requestMatchers("/townList").authenticated()
                .requestMatchers("/deleteTown/**").hasRole("ADMIN")
                .anyRequest().denyAll())
                .httpBasic(Customizer.withDefaults()).
                formLogin(Customizer.withDefaults());
        return http.build();
    }
}
