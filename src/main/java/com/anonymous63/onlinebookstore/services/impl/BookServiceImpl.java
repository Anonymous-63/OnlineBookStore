package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.exceptions.ResourceNotFoundException;
import com.anonymous63.onlinebookstore.models.Book;
import com.anonymous63.onlinebookstore.models.Category;
import com.anonymous63.onlinebookstore.payloads.dtos.BookDto;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;
import com.anonymous63.onlinebookstore.repositories.BookRepo;
import com.anonymous63.onlinebookstore.repositories.CategoryRepo;
import com.anonymous63.onlinebookstore.repositories.UserRepo;
import com.anonymous63.onlinebookstore.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceImpl extends CrudServiceImpl<Book, BookDto, Long> implements BookService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final CategoryRepo categoryRepo;

    public BookServiceImpl(UserRepo userRepo, CategoryRepo categoryRepo, BookRepo repository, ModelMapper modelMapper) {
        super(Book.class, BookDto.class);
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CrudResponse findBookByCategoryId(Long id, Integer page, Integer size, String sortBy, String sortDir) {
        Category category = this.categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class.getSimpleName(), "id", id));
        return null;
    }

    @Override
    public CrudResponse findBookByUserId(Long id, Integer page, Integer size, String sortBy, String sortDir) {
        return null;
    }
}
