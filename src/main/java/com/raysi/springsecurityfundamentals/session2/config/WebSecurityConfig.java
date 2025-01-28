package com.raysi.springsecurityfundamentals.session2.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        return null;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // Never use it in productions
        // It is for plain text
        return NoOpPasswordEncoder.getInstance();
    }
}
