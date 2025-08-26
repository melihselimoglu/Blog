package com.SpringBoot.blog.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.blog.domain.dtos.CreateTagsRequest;
import com.SpringBoot.blog.domain.dtos.TagResponse;
import com.SpringBoot.blog.domain.entities.Tag;
import com.SpringBoot.blog.mappers.TagMapper;
import com.SpringBoot.blog.services.TagService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





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

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> savedTags=tagService.createTags(createTagsRequest.getNames());
        List<TagResponse> createdTagResponses = savedTags.stream().map(tagMapper::toTagResponse).toList();
        return new ResponseEntity<>(
            createdTagResponses, 
            HttpStatus.CREATED);
    }
    
}
