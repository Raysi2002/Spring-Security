package com.raysi.springsecurityfundamentals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();
        var e1 = User.withUsername("bill")
                .password("Raysi@2002")
                .authorities("home")
                .build();
        uds.createUser(e1);
        return uds;
    }
}
