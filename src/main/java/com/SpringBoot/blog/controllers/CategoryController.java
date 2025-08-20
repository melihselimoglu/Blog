package com.SpringBoot.blog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



import com.SpringBoot.blog.domain.dtos.CategoryDto;
import com.SpringBoot.blog.services.CategoryService;
import com.SpringBoot.blog.mappers.CategoryMapper;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryService.listCategories()
            .stream().map(categoryMapper::toDto)
            .toList();
        return ResponseEntity.ok(categories);
    }
    
}