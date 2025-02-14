package com.raysi.springsecurity.session1.controller;

import com.raysi.springsecurity.session1.entity.Users;
import com.raysi.springsecurity.session1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user){
        try{
            userService.saveUser(user);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(user);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in controller layer");
        }
    }
}
