package com.anonymous63.onlinebookstore.controllers;

import com.anonymous63.onlinebookstore.payloads.dtos.CategoryDto;
import com.anonymous63.onlinebookstore.payloads.response.ApiResponse;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;
import com.anonymous63.onlinebookstore.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET /api/users/
    @GetMapping("/")
    public CrudResponse getAllCategory(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                       @RequestParam(value = "size", defaultValue = "100", required = false) int size,
                                       @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {
        return this.categoryService.findAll(page, size, sortBy, sortDir);
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return this.categoryService.findById(id);
    }

    // POST /api/users/
    @PostMapping("/")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long id) {
        categoryDto.setId(id);
        return this.categoryService.update(categoryDto, id);
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        this.categoryService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Category Deleted Successfully", true));
    }
}
