package com.example.job_management_system.security;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
             http
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                 .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/v1/jobs/**").permitAll()
                           .requestMatchers("/api/v1/auth/**").permitAll()
                           .anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login
                    .loginProcessingUrl("/api/v1/auth/login")
                    .defaultSuccessUrl("/api/v1/auth/me", true)
                    .failureForwardUrl("/login?error=true")
                    .permitAll();
                })
                .logout(logout -> {
                    logout.logoutUrl("/api/v1/auth/logout")
                          .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK))
                          .invalidateHttpSession(true)
                          .deleteCookies("JSESSIONID");
                })
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                );
         return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("Neo")
                                .password(new BCryptPasswordEncoder().encode("neophenon!@#"))
                                .authorities("ADMIN")
                                .accountExpired(false)
                                .accountLocked(false)
                                .credentialsExpired(false)
                                .build();

        UserDetails user2 = User.withUsername("Neli")
                                .password(new BCryptPasswordEncoder().encode("neophenon!@#"))
                                .authorities("ADMIN")
                                .accountExpired(false)
                                .accountLocked(false)
                                .credentialsExpired(false)
                                .build();
        return new InMemoryUserDetailsManager(user,user2);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
