package com.SpringBoot.blog.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.SpringBoot.blog.domain.PostStatus;
import com.SpringBoot.blog.domain.entities.Category;
import com.SpringBoot.blog.domain.entities.Post;
import com.SpringBoot.blog.domain.entities.Tag;
import com.SpringBoot.blog.domain.entities.User;
import com.SpringBoot.blog.repositories.PostRepository;
import com.SpringBoot.blog.services.CategoryService;
import com.SpringBoot.blog.services.PostService;
import com.SpringBoot.blog.services.TagService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId); 
            Tag tag = tagService.getTagById(tagId);
            postRepository.findAllByStatusAndCategoryAndTagsContaining(
                PostStatus.PUBLISHED, 
                category, 
                tag
            );
        }
        if(categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(
                PostStatus.PUBLISHED, 
                category
            );
        }
        if(tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(
                PostStatus.PUBLISHED,
                tag
            );
        }
        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }
    
}
