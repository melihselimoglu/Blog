package com.SpringBoot.blog.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

    @NotBlank(message = "Name is required")
    @Size(min=2,max = 50, message = "Name must be less than 50 and more than 2 characters")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "Name can only contain alphanumeric characters and spaces")
    private String name;
    private String description;
    
}
