package com.raysi.springsecurity.session1.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * UserPrincipal is a custom implementation of Spring Security's UserDetails.
 * It wraps the Users entity and provides authentication-related details.
 */
public class UserPrincipal implements UserDetails {

    private final Users user;

    /**
     * Constructor to initialize UserPrincipal with a Users object.
     * @param user The user entity containing user details.
     */
    public UserPrincipal(Users user) {
        this.user = user;
    }

    /**
     * Returns the authorities (roles/permissions) granted to the user.
     * In this case, all users are given the "USERS" role.
     *
     * @return Collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USERS"));
    }

    /**
     * Returns the password of the user for authentication.
     * 🚨 **BUG**: This method is returning `user.getUserName()`, but it should return `user.getPassword()`.
     *
     * @return User's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword(); // ✅ Fixing incorrect return value
    }

    /**
     * Returns the username of the user for authentication.
     * 🚨 **BUG**: This method is returning `user.getPassword()`, but it should return `user.getUserName()`.
     *
     * @return User's username.
     */
    @Override
    public String getUsername() {
        return user.getUserName(); // ✅ Fixing incorrect return value
    }

    /**
     * Indicates whether the user's account has expired.
     * Returning `true` means the account is always valid.
     *
     * @return true (account never expires).
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or not.
     * Returning `true` means the account is never locked.
     *
     * @return true (account never locked).
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     * Returning `true` means credentials never expire.
     *
     * @return true (credentials never expire).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * Returning `true` means the user is always enabled.
     *
     * @return true (user is always enabled).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}