package com.app.backend.api.config.spring_security;

import com.app.backend.api.config.jwt.authFilterJwt.JwtAuthenticationFilter;
import com.app.backend.api.config.jwt.jsonAuth.JsonAccessDeniedHandler;
import com.app.backend.api.config.jwt.jsonAuth.JsonAuthEntryPoint;
import com.app.backend.api.config.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SpringSecurityClass {

    @Bean
    public JsonAuthEntryPoint jsonAuthEntryPoint() {
        return new JsonAuthEntryPoint();
    }

    @Bean
    public JsonAccessDeniedHandler jsonAccessDeniedHandler() {
        return new JsonAccessDeniedHandler();
    }

    // Bean del filtro JWT (Spring lo construye con sus dependencias)
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService,
                                                           JsonAuthEntryPoint entryPoint) {
        return new JwtAuthenticationFilter(jwtService, entryPoint);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JsonAuthEntryPoint entryPoint,
                                           JsonAccessDeniedHandler deniedHandler,
                                           JwtAuthenticationFilter jwtAuthenticationFilter // ← ¡inyectado acá!
    ) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(req -> {
                    var c = new CorsConfiguration();
                    c.setAllowedOrigins(List.of("*"));
                    c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
                    c.setAllowedHeaders(List.of("*"));
                    return c;
                }))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint((req, res, ex) -> entryPoint.commence(req, res, ex))
                        .accessDeniedHandler(deniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
                )
                // Usamos la variable del parámetro: jwtAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
