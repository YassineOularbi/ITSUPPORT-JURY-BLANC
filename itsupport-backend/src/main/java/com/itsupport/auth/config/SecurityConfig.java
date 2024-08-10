package com.itsupport.auth.config;

import com.itsupport.auth.filter.AuthFilter;
import com.itsupport.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class for setting up web security, including authentication and authorization.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthFilter authFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object to configure.
     * @return the configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                                .requestMatchers("/api/user/admin/**", "/api/equipment/admin/**","/api/breakdown/admin/**", "/api/ticket/client/**").hasAuthority(Role.ADMIN.name())
                                .requestMatchers("/api/equipment/client/**", "/api/ticket/client/**").hasAuthority(Role.CLIENT.name())
                                .requestMatchers("/api/ticket/client/**").hasAuthority(Role.TECHNICIAN.name())
                                .requestMatchers(HttpMethod.PUT,"/api/user/update-client/**").hasAnyAuthority(Role.ADMIN.name(), Role.CLIENT.name())
                                .requestMatchers(HttpMethod.PUT,"/api/update-technician/**").hasAnyAuthority(Role.ADMIN.name(), Role.TECHNICIAN.name())
                                .requestMatchers(HttpMethod.GET,"/api/breakdown/get-all-breakdowns").hasAnyAuthority(Role.ADMIN.name(), Role.CLIENT.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
