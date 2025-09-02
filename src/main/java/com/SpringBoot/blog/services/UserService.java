package com.SpringBoot.blog.services;

import java.util.UUID;

import com.SpringBoot.blog.domain.entities.User;

public interface UserService {
    User getUserById(UUID id);
}
