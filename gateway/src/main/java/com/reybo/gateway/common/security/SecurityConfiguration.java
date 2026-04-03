package com.reybo.gateway.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())

                .oauth2Login(oauth2 -> oauth2
                        .authenticationSuccessHandler((webFilterExchange, authentication) -> {
                            // После успешного логина редиректим на главную
                            webFilterExchange.getExchange().getResponse().setStatusCode(
                                    org.springframework.http.HttpStatus.FOUND
                            );
                            webFilterExchange.getExchange().getResponse().getHeaders().setLocation(
                                    URI.create("/")
                            );
                            return webFilterExchange.getChain().filter(webFilterExchange.getExchange());
                        })
                )

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(
                                        new ReactiveJwtAuthenticationConverterAdapter(jwtMono -> {

                                                    return new JwtAuthenticationToken(
                                                            jwtMono,
                                                            extractRoles(jwtMono)
                                                    );
                                        })
                                )
                        )
                )

                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                "/auth/**",
                                "/login/**",
                                "/oauth2/**",
                                "/realms/**",
                                "/resources/**"
                        ).permitAll()
                        .pathMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .pathMatchers("/api/v1/**").hasAnyRole("ADMIN", "USER")
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