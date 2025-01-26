package com.raysi.springsecurityfundamentals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return """
                <html lang="en">
                <head>
                    <title>Document</title>
                </head>
                <style>
                   \s
                </style>
                <body>
                    <h1 style="text-align: center">Welcome Home</h1>
                    <p>Finally I am learning spring security</p>
                </body>
                </html>
                """;
    }
}
