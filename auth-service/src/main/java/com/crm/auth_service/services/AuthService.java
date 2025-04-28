package com.crm.auth_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crm.auth_service.dtos.AuthRequest;
import com.crm.auth_service.dtos.UserDTO;
import com.crm.auth_service.models.UserModel;
import com.crm.auth_service.repositories.UserModelRepository;

@Service
public class AuthService {

    @Autowired
    private UserModelRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModel userModel = new UserModel();
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setRole(user.getRole());
        repository.save(userModel);
        return "user added to the system";
    }


    public boolean checkUserAuthentication(AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        return authenticate.isAuthenticated();
    }

    public String getUserRole(AuthRequest authRequest) {
        return repository.findByUsername(authRequest.getUsername()).get().getRole().toString();
    }

    public String generateToken(AuthRequest authRequest) {
        if (!checkUserAuthentication(authRequest)) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwtService.generateToken(authRequest.getUsername(), "ROLE_"+getUserRole(authRequest));
    }

    public String validateToken(String token) {
        return jwtService.validateToken(token);
    }


}

