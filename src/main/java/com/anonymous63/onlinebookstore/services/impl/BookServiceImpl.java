package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.models.Book;
import com.anonymous63.onlinebookstore.payloads.dtos.BookDto;
import com.anonymous63.onlinebookstore.repositories.BookRepo;
import com.anonymous63.onlinebookstore.services.BookService;
import org.modelmapper.ModelMapper;

public class BookServiceImpl extends CrudServiceImpl<Book, BookDto, Long> implements BookService {
    public BookServiceImpl(BookRepo repository, ModelMapper modelMapper) {
        super(Book.class, BookDto.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
}
