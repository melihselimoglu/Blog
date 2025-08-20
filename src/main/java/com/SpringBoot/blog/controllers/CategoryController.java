package com.SpringBoot.blog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



import com.SpringBoot.blog.domain.dtos.CategoryDto;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    
    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        
    }
    
}