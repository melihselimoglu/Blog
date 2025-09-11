package com.SpringBoot.blog.services;

import java.util.List;
import java.util.UUID;

import com.SpringBoot.blog.domain.CreatePostRequest;
import com.SpringBoot.blog.domain.UpdatePostRequest;
import com.SpringBoot.blog.domain.entities.Post;
import com.SpringBoot.blog.domain.entities.User;

public interface PostService {
    Post getPost(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
}
