package com.raysi.springsecurity.session1.service;

import com.raysi.springsecurity.session1.entity.UserPrincipal;
import com.raysi.springsecurity.session1.entity.Users;
import com.raysi.springsecurity.session1.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling user authentication.
 * Implements UserService (which likely extends UserDetailsService from Spring Security).
 */
@Service // Marks this class as a Spring-managed service component.
public class UsersServiceImplementation implements UserService {

    private final UserRepository userRepository; // Repository for interacting with the database.

    /**
     * Constructor-based dependency injection of UserRepository.
     * This ensures that the repository is provided when this service is instantiated.
     */
    public UsersServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by username for authentication.
     * This method is called by Spring Security when a user attempts to log in.
     *
     * @param username the username entered during authentication.
     * @return UserDetails, which Spring Security uses for authentication and authorization.
     * @throws UsernameNotFoundException if the user does not exist in the database.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user details from the database using the repository.
        Users user = userRepository.findByUserName(username);

        // If user is not found, throw an exception required by Spring Security.
        if (user == null) {
            System.out.println("No such user found"); // Debugging statement (can be replaced with a logger).
            throw new UsernameNotFoundException("No such user found");
        }

        // Return a UserPrincipal object, which wraps the user details for authentication.
        return new UserPrincipal(user);
    }

    @Override
    public void saveUser(Users user) {
        if (user == null)
            throw new RuntimeException("User can't be null");
        try{
            userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in Service layer");
        }
    }
}