package com.raysi.springsecurity.session1.controller;

import com.raysi.springsecurity.session1.entity.Users;
import com.raysi.springsecurity.session1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController handles user-related HTTP requests such as registration.
 * This class integrates with Spring Security by encoding passwords before storing them.
 */
@RestController  // Marks this class as a REST controller, handling HTTP requests.
public class UserController {

    private final UserService userService;  // Service layer dependency for user management.

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    // BCryptPasswordEncoder is used for **secure password hashing**. Strength set to 12.

    /**
     * Constructor-based Dependency Injection for UserService.
     * This is the recommended way to inject dependencies in Spring.
     *
     * @param userService The user service implementation.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles user registration requests.
     * It accepts a JSON payload with user details, hashes the password, and saves the user.
     *
     * @param user The user entity received from the client.
     * @return ResponseEntity containing the registered user and appropriate HTTP status.
     */
    @PostMapping("/register")  // Maps HTTP POST requests to "/register" endpoint.
    public ResponseEntity<Users> register(@RequestBody Users user) {
        try {
            // 🔹 **Secure Password Hashing**
            // The received password is **hashed** using BCrypt before storing it in the database.
            user.setPassword(encoder.encode(user.getPassword()));

            // 🔹 **Saving User**
            // Calls the service layer to persist the new user.
            userService.saveUser(user);

            // 🔹 **Returning Success Response**
            // If successful, returns HTTP 202 (ACCEPTED) status with the registered user.
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } catch (Exception e) {
            // 🔹 **Exception Handling**
            // If an error occurs, a runtime exception is thrown.
            // ⚠️ **Improvement Needed**: Do not throw a generic `RuntimeException` here.
            throw new RuntimeException("Something went wrong in controller layer");
        }
    }
}