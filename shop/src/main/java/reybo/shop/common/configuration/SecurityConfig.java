package reybo.shop.common.configuration;

import reybo.shop.common.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/shop/styles/**",
                                "/shop/header/media/**"
                        ).permitAll()
                        .requestMatchers(
                                "/login",
                                "/register"
                        ).permitAll()
                        .requestMatchers("/shop").authenticated()
                        .requestMatchers("/shop/search").permitAll()
                        .requestMatchers("/shop/login").permitAll()
                        .requestMatchers("/shop/register").permitAll()
                        .requestMatchers("/shop/forgot").permitAll()
                        .requestMatchers("/shop/styles/**").permitAll()
                        .requestMatchers("/shop/header/media/**").permitAll()
                        .requestMatchers("/shop/orders**").authenticated()
                        .requestMatchers("/shop/account/**").authenticated()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}