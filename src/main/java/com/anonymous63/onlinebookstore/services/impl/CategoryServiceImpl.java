package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.models.Category;
import com.anonymous63.onlinebookstore.payloads.dtos.CategoryDto;
import com.anonymous63.onlinebookstore.repositories.CategoryRepo;
import com.anonymous63.onlinebookstore.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends CrudServiceImpl<Category, CategoryDto, Long> implements CategoryService {
    public CategoryServiceImpl(CategoryRepo repository, ModelMapper modelMapper) {
        super(Category.class, CategoryDto.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
}
