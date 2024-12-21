package com.wsda.bikegarage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/").permitAll()
                                .requestMatchers("/accettazione").hasRole("ac")
                                .requestMatchers("/riparazione").hasRole("mc")
                                .requestMatchers("/cassa").hasRole("ca")
                                .requestMatchers("/magazzino").hasRole("mg")
                                .requestMatchers("/statoriparazione").permitAll()
                                .anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginPage("/")
                        .permitAll())
                .logout((logout) -> logout.permitAll()).csrf(csrf -> csrf.disable());
        ;

        return http.build();
    }

}