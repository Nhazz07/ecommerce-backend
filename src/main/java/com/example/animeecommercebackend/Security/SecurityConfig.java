package com.example.animeecommercebackend.Security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration // Tell that This class contains configuration that Spring needs to load.
@RequiredArgsConstructor
public class SecurityConfig {
   private final JwtAuthenticationFilter jwtAuthenticationFilter;

   // password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // security filter chains
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                // disable csrf
                .csrf(csrf -> csrf.disable())

                // enable CORS
                .cors(cors-> cors.configurationSource(corsConfiguration()))

                // jwt is stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // authorization rules
                .authorizeHttpRequests(auth->auth

                        // Public endpoint
                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login",

                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // every other end points requires JWT
                        .anyRequest().authenticated()
                )

                // add jwt filter
                .addFilterBefore(jwtAuthenticationFilter,
                                  UsernamePasswordAuthenticationFilter.class
                );
        return httpSecurity.build();
    }

    // cors Configuration
    private CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost:3000",
                        "http://localhost:5173"
                )
        );

        // http method
        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"
                )
        );
        // header
        configuration.setAllowedHeaders(
                List.of("*")
        );
        // credentials
        configuration.setAllowCredentials(true);

        // apply cors configuration to all endpoints
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(
                "/**",
                configuration
                );
        return source;
    }

}
