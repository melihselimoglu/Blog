package com.SpringBoot.blog.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.blog.domain.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    
    
}
