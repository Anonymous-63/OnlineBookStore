package com.anonymous63.onlinebookstore.payloads.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDto {

    private Long id;

    @NotEmpty(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @NotEmpty(message = "Author is required")
    @Size(min = 3, max = 50, message = "Author must be between 3 and 50 characters")
    private String author;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private String imageName;

    @NotEmpty(message = "Publisher is required")
    @Size(min = 3, max = 50, message = "Publisher must be between 3 and 50 characters")
    private String publisher;

    @NotEmpty(message = "Publication Date is required")
    private String publicationDate;

    @NotEmpty(message = "Language is required")
    private String language;

    @NotEmpty(message = "Pages is required")
    private int pages;

    @NotEmpty(message = "List Price is required")
    private double listPrice;

    @NotEmpty(message = "Our Price is required")
    private double ourPrice;

    @NotEmpty(message = "Quantity is required")
    private int quantity;

    @NotEmpty(message = "Active is required")
    private boolean active;

    private CategoryDto category;
    private UserDto user;

}
