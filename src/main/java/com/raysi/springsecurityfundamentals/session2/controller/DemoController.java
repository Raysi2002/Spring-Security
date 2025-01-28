package com.raysi.springsecurityfundamentals.session2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public String demo(){
        return """
                Demo for session 2
                """;

    }
}
