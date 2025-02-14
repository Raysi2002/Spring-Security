package com.raysi.springsecurity.session1.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Custom UserService interface extending Spring Security's UserDetailsService.
 *
 * Spring Security requires a UserDetailsService implementation to load user-specific data.
 * By extending UserDetailsService, we ensure that our service provides authentication functionality.
 */
@Service // Marks this interface as a Spring-managed service.
public interface UserService extends UserDetailsService {
    // No additional methods for now, but this allows future extensions.
}