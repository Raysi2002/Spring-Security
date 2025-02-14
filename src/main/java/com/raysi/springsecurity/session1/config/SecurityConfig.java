package com.raysi.springsecurity.session1.config;

// Importing required Spring Security and Configuration-related classes
import com.raysi.springsecurity.session1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig class is responsible for configuring security settings for the application.
 * It defines how authentication and authorization should be handled using Spring Security.
 */
@Configuration // Marks this class as a Spring Configuration class (equivalent to XML configuration in Spring)
@EnableWebSecurity // Enables Spring Security for the application
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService){
        this.userService = userService;
    }


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
                // Disabling CSRF (Cross-Site Request Forgery) protection.
                // This is generally not recommended for web applications but might be necessary for APIs where CSRF protection is handled differently (e.g., token-based authentication).
                .csrf(customizer -> customizer.disable())

                // Configuring authorization to require authentication for all incoming requests.
                // This means no request can be accessed without proper authentication.
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())

                // Enabling form-based login with default configurations.
                // This allows users to authenticate via a login page provided by Spring Security.
                .formLogin(Customizer.withDefaults())

                // Enabling HTTP Basic authentication.
                // This is useful for APIs or development/testing purposes but should not be used in production without HTTPS.
                .httpBasic(Customizer.withDefaults())

                // Building the HttpSecurity configuration.
                .build();
    }

//    @Bean // Marks this method as a Spring bean, making it available for dependency injection.
//    public UserDetailsService userDetailsService() {
//
//        // Creating a user with the username "aashu", password "123", and role "ADMIN".
//        // withDefaultPasswordEncoder() is used to encode the password, but it's not recommended for production.
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()  // Uses a basic password encoder (not secure for production use).
//                .username("aashu")             // Sets username for this user.
//                .password("123")               // Sets plaintext password (only for testing purposes).
//                .roles("ADMIN")                // Assigns "ADMIN" role to this user.
//                .build();                       // Builds the UserDetails object.
//
//        // Creating another user with username "bunu", password "123", and role "USER".
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("bunu")
//                .password("123")
//                .roles("USER")
//                .build();
//
//        // InMemoryUserDetailsManager stores user details in memory.
//        // This is useful for testing but should not be used in production as data is lost when the application restarts.
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // Sets the password encoder. NoOpPasswordEncoder is NOT recommended for production.
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        // Sets the userDetailsService, which is responsible for loading user details from the database.
        provider.setUserDetailsService(userService);

        return provider;
    }
}