package ru.money.tracker.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import ru.money.tracker.core.security.CustomAuthorityOpaqueTokenIntrospector;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final OpaqueTokenIntrospector introspector;

    /**
     * Configures basic security handler per HTTP session.
     * <p>
     * <ul>
     * <li>Stateless session (no session kept server-side)</li>
     * <li>CORS set up</li>
     * <li>Require authentication for the /api paths</li>
     * <li>access token converted into Spring token</li>
     * </ul>
     *
     * @param http security configuration
     * @throws Exception any error
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/api").authenticated();
                    registry.anyRequest().permitAll();
                })
                .oauth2ResourceServer(rs -> rs.opaqueToken(configurer -> configurer.introspector(introspector())));

        return http.build();
    }

    private OpaqueTokenIntrospector introspector() {
        return new CustomAuthorityOpaqueTokenIntrospector(introspector);
    }
}
