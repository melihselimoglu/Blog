package com.SpringBoot.blog.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authentication(String email, String password);
    String generateToken(UserDetails userDetails);
}
