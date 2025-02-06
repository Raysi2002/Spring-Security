package com.raysi.springsecurity.session1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(){
        return """
                <!doctype html>
                <html lang="en">
                <head>
                    <title>Spring Security</title>
                </head>
                <body>
                    <header>
                        <h1 style="text-align: center">Spring Security</h1>
                    </header>
                </body>
                </html>
                """;
    }
}
