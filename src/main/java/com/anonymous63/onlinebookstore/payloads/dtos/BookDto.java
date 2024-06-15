package com.anonymous63.onlinebookstore.payloads.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Author must contain only alphabets and spaces")
    private String author;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private String imageName;

    @NotEmpty(message = "Publisher is required")
    @Size(min = 3, max = 50, message = "Publisher must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Publisher must contain only alphabets and spaces")
    private String publisher;

    @NotEmpty(message = "Publication Date is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d{2}$", message = "Publication Date must be in MM/dd/yyyy format")
    private String publicationDate;

    @NotEmpty(message = "Language is required")
    @Size(min = 3, max = 20, message = "Language must be between 3 and 20 characters")
    private String language;

    @NotEmpty(message = "Pages is required")
    @Pattern(regexp = "^[0-9]+$", message = "Pages must contain only numbers")
    private int pages;

    @NotEmpty(message = "List Price is required")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "List Price must be a number with up to 2 decimal places")
    private double listPrice;

    @NotEmpty(message = "Our Price is required")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Our Price must be a number with up to 2 decimal places")
    private double ourPrice;

    @NotEmpty(message = "Quantity is required")
    @Pattern(regexp = "^[0-9]+$", message = "Quantity must contain only numbers")
    private int quantity;

    @NotEmpty(message = "Active is required")
    private boolean active;

    private CategoryDto category;
    private UserDto user;

}
