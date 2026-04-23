package com.reybo.gateway.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())

                .oauth2Login(oauth2 -> oauth2
                        .authenticationSuccessHandler((webFilterExchange, authentication) -> {
                            log.info("Login SUCCESS, redirecting to https://reybo.ru");

                            ServerWebExchange exchange = webFilterExchange.getExchange();
                            ServerHttpResponse response = exchange.getResponse();

                            response.setStatusCode(HttpStatus.FOUND);
                            response.getHeaders().setLocation(URI.create("https://reybo.ru"));

                            return Mono.empty();
                        })
                        .authenticationFailureHandler((webFilterExchange, exception) -> {
                            log.error("Login FAILED: {}", exception.getMessage());

                            ServerWebExchange exchange = webFilterExchange.getExchange();
                            ServerHttpResponse response = exchange.getResponse();

                            response.setStatusCode(HttpStatus.FOUND);
                            response.getHeaders().setLocation(URI.create("/login?error"));

                            return Mono.empty();
                        })
                )

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(
                                        new ReactiveJwtAuthenticationConverterAdapter(jwtMono ->
                                                new JwtAuthenticationToken(jwtMono, extractRoles(jwtMono))
                                        )
                                )
                        )
                )

                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**", "/login/**", "/oauth2/**").permitAll()
                        .anyExchange().authenticated()
                )

                .build();
    }

    private Collection<GrantedAuthority> extractRoles(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }

        return authorities;
    }
}