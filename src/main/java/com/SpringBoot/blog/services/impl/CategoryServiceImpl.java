package com.SpringBoot.blog.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.blog.domain.entities.Category;
import com.SpringBoot.blog.repositories.CategoryRepository;
import com.SpringBoot.blog.services.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if(categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category with this name already exists"+ category.getName());
        }
        return categoryRepository.save(category);
    }
    
}
