package com.anonymous63.onlinebookstore.payloads.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only alphabets and spaces")
    private String name;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;
}
