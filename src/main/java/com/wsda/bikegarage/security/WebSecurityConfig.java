package com.wsda.bikegarage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/").permitAll()
                                .requestMatchers("/accettazione/**").hasAuthority("ac")
                                .requestMatchers("/riparazione").hasAuthority("mc")
                                .requestMatchers("/cassa").hasAuthority("ca")
                                .requestMatchers("/magazzino").hasAuthority("mg")
                                .requestMatchers("/statoriparazione").permitAll()
                                .anyRequest().permitAll())
                .exceptionHandling(exception -> exception.accessDeniedPage("/accessDenied"))
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/loginDone")
                        .failureUrl("/loginDone")
                        .permitAll())
                .logout((logout) -> logout.permitAll().logoutSuccessUrl("/")).csrf(csrf -> csrf.disable()).userDetailsService(userDetailsService);
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}