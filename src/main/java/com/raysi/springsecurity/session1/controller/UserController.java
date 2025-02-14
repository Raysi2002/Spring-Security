package com.raysi.springsecurity.session1.controller;

import com.raysi.springsecurity.session1.entity.Users;
import com.raysi.springsecurity.session1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user){
        try{
            user.setPassword(encoder.encode(user.getPassword()));
            userService.saveUser(user);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(user);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in controller layer");
        }
    }

}
