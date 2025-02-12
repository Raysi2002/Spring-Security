package com.raysi.springsecurity.session1.config;

// Importing required Spring Security and Configuration-related classes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig class is responsible for configuring security settings for the application.
 * It defines how authentication and authorization should be handled using Spring Security.
 */
@Configuration // Marks this class as a Spring Configuration class (equivalent to XML configuration in Spring)
@EnableWebSecurity // Enables Spring Security for the application
public class SecurityConfig {

    /**
     * Defines the security filter chain for handling security configurations.
     *
     * @param httpSecurity The HttpSecurity object allows configuring security features.
     * @return SecurityFilterChain that contains security configurations.
     * @throws Exception if any security configuration fails.
     */
    @Bean // Marks this method as a Bean that Spring will manage
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        // Disables CSRF (Cross-Site Request Forgery) protection.
//        // This is generally safe for APIs (especially stateless ones), but should be enabled for web applications with sessions.
//        httpSecurity.csrf(customizer -> customizer.disable());
//
//        // Requires authentication for all incoming requests.
//        // No endpoint is allowed to be accessed without authentication.
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//
//        // Enables form-based authentication with default settings.
//        // This will redirect users to a login page for authentication.
//        httpSecurity.formLogin(Customizer.withDefaults());
//
//        // Enables HTTP Basic authentication.
//        // Useful for API authentication where credentials are sent in the request header.
//        httpSecurity.httpBasic(Customizer.withDefaults());
//
//        // Configures session management to be stateless.
//        // This means Spring Security will not store user sessions (ideal for REST APIs where session persistence is not needed).
//        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        // Builds and returns the SecurityFilterChain with the configured settings.
//        return httpSecurity.build();

        /// Using Builder Pattern
        return httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}