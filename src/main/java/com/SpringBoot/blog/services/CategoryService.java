package com.SpringBoot.blog.services;

import java.util.List;

import com.SpringBoot.blog.domain.entities.Category;

public interface CategoryService {
    List<Category>  listCategories();
    Category createCategory(Category category);
}
