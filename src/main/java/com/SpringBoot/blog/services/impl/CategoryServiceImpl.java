package com.SpringBoot.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.SpringBoot.blog.domain.entities.Category;
import com.SpringBoot.blog.repositories.CategoryRepository;
import com.SpringBoot.blog.services.CategoryService;

import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public void deleteCategory(UUID id) {
       Optional<Category> category = categoryRepository.findById(id);
       if(category.isPresent()) {
           if(!category.get().getPosts().isEmpty()){
                throw new IllegalArgumentException("Cannot delete category with posts");
              }  
            categoryRepository.deleteById(id);   
       }
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id" + id));
    }
    
}
