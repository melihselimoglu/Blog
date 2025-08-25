package com.SpringBoot.blog.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.blog.domain.entities.Tag;
import com.SpringBoot.blog.repositories.TagRepository;
import com.SpringBoot.blog.services.TagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }
    
}
