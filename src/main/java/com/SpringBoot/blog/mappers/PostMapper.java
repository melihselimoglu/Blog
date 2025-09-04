package com.SpringBoot.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.SpringBoot.blog.domain.CreatePostRequest;
import com.SpringBoot.blog.domain.dtos.CreatePostRequestDto;
import com.SpringBoot.blog.domain.dtos.PostDto;
import com.SpringBoot.blog.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    
    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);
}
