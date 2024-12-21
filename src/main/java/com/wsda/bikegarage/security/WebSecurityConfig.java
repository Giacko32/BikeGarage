package com.wsda.bikegarage.security;

import com.wsda.bikegarage.repositories.ImpiegatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
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
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/loginSuccesful")
                        .permitAll())
                .logout((logout) -> logout.permitAll()).csrf(csrf -> csrf.disable()).userDetailsService(userDetailsService);
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}