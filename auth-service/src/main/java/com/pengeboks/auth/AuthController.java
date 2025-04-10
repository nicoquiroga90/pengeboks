package com.pengeboks.auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping("/login")
    public String login(@RequestParam String email) {
        return "Simulated login for: " + email;
    }
}