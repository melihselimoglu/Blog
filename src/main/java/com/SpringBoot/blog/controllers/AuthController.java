package com.SpringBoot.blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.blog.domain.dtos.AuthResponse;
import com.SpringBoot.blog.domain.dtos.LoginRequest;
import com.SpringBoot.blog.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authentication(
            loginRequest.getEmail(), 
            loginRequest.getPassword()
        );
        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder()
            .token(tokenValue)
            .expiresIn(86400) 
            .build();
            return ResponseEntity.ok(authResponse);
    }

}
