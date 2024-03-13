package org.example.vkinternship.config;

import lombok.RequiredArgsConstructor;
import org.example.vkinternship.audit.AuthFilter;
import org.example.vkinternship.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthFilter authFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("api/auth/**").permitAll()
                                .requestMatchers(HttpMethod. GET, "/api/posts/**").hasAuthority("posts:read")
                                .requestMatchers(HttpMethod.POST, "/api/posts/**").hasAuthority("posts:create")
                                .requestMatchers(HttpMethod.PUT, "/api/posts/**").hasAuthority("posts:update")
                                .requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasAuthority("posts:delete")
                                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAuthority("users:read")
                                .requestMatchers(HttpMethod.POST, "/api/users/**").hasAuthority("users:create")
                                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasAuthority("users:update")
                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority("users:delete")
                                .requestMatchers(HttpMethod.GET, "/api/albums/**").hasAuthority("albums:read")
                                .requestMatchers(HttpMethod.POST, "/api/albums/**").hasAuthority("albums:create")
                                .requestMatchers(HttpMethod.PUT, "/api/albums/**").hasAuthority("albums:update")
                                .requestMatchers(HttpMethod.DELETE, "/api/albums/**").hasAuthority("albums:delete")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
