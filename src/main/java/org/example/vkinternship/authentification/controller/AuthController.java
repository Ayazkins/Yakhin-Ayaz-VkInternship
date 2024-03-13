package org.example.vkinternship.authentification.controller;

import org.example.vkinternship.authentification.AuthRequest;
import org.example.vkinternship.authentification.AuthResponse;
import org.example.vkinternship.authentification.RegisterRequest;
import org.example.vkinternship.authentification.RegisterResponse;
import org.example.vkinternship.authentification.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public RegisterResponse registerResponse(RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/authentication")
    public AuthResponse registerResponse(AuthRequest request) {
        return authService.auth(request);
    }
}
