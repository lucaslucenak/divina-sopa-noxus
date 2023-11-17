package com.lucalucenak.Noxus.config;

import com.lucalucenak.Noxus.utils.SecurityFilterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilterUtil securityFilterUtil;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.cors(Customizer.withDefaults());

        httpSecurity.headers().frameOptions().disable();

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console" + "/**")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/authentication/login")).permitAll()
                // Address Routes
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/address")).hasRole("USER")
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/address/find-by-client-account-id/{clientAccountId}")).hasRole("USER")
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.PUT, "/address/{addressId}")).hasRole("USER")
                // Client Account Routes
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/client-account")).permitAll() // Create account
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/client-account/{clientAccountId}")).hasRole("USER")
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.PUT, "/client-account/{clientAccountId}")).hasRole("USER")
                // Order Routes
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/order")).hasRole("USER")
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/order/find-by-client-account-id/{clientAccountId}")).hasRole("USER")
                // Product Routes
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/product")).permitAll()

                .anyRequest().hasRole("ADMIN")
        );

        httpSecurity.addFilterBefore(securityFilterUtil, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
