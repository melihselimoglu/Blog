package com.SpringBoot.blog.domain.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.SpringBoot.blog.domain.PostStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePostRequestDto {
    
    @NotNull(message = "Id cannot be null")
    private UUID id;

    @NotBlank(message = "Title cannot be null")
    @Size(min = 5, max = 200, message = "Title must be between 5 and 200 characters")
    private String title;

    @NotBlank(message = "Content cannot be null")
    @Size(min = 20, max = 50000, message = "Content must be between {min} and {max} characters")
    private String content;

    @NotNull(message = "CategoryId cannot be null")
    private UUID categoryId;

    @Builder.Default
    @Size(max = 10, message = "A post can have at most {max} tags")
    private Set<UUID> tagIds = new HashSet<>();

    @NotNull(message = "Status cannot be null")
    private PostStatus status;
}
