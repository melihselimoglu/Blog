package com.SpringBoot.blog.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.blog.domain.dtos.TagResponse;
import com.SpringBoot.blog.domain.entities.Tag;
import com.SpringBoot.blog.mappers.TagMapper;
import com.SpringBoot.blog.services.TagService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;
    
    @GetMapping 
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags.stream()
            .map(tagMapper::toTagResponse)
            .toList();
        return ResponseEntity.ok(tagResponses);
    }
}
