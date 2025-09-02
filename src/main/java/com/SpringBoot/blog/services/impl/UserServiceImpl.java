package com.SpringBoot.blog.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.SpringBoot.blog.domain.entities.User;
import com.SpringBoot.blog.repositories.UserRepository;
import com.SpringBoot.blog.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
}
