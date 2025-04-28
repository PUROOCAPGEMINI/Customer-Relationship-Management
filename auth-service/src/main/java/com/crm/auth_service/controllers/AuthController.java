package com.crm.auth_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crm.auth_service.dtos.AuthRequest;
import com.crm.auth_service.dtos.UserDTO;
import com.crm.auth_service.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService service;


    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDTO user) {
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest authRequest) {
        return service.generateToken(authRequest);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        return service.validateToken(token);
    }
}
