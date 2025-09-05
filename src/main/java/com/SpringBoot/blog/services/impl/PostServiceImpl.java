package com.SpringBoot.blog.services.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.SpringBoot.blog.domain.CreatePostRequest;
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

    private static final int WORDS_PER_MINUTE = 200;

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

    @Override
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setStatus(createPostRequest.getStatus());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculateReadingTime(createPostRequest.getContent()));

        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
        newPost.setCategory(category);

        Set<UUID> tagIds = createPostRequest.getTagIds();
        List<Tag> tags = tagService.getTagByIds(tagIds);
        newPost.setTags(new HashSet<>(tags));

        return postRepository.save(newPost);
    }
    
    private Integer calculateReadingTime(String content) {
        if(content == null || content.isEmpty()) {
            return 0;
        }
       int wordCount = content.trim().split("\\s+").length;
       return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }

}
