package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.exceptions.ResourceNotFoundException;
import com.anonymous63.onlinebookstore.models.Book;
import com.anonymous63.onlinebookstore.models.Category;
import com.anonymous63.onlinebookstore.models.User;
import com.anonymous63.onlinebookstore.payloads.dtos.BookDto;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponseConverter;
import com.anonymous63.onlinebookstore.repositories.BookRepo;
import com.anonymous63.onlinebookstore.repositories.CategoryRepo;
import com.anonymous63.onlinebookstore.repositories.UserRepo;
import com.anonymous63.onlinebookstore.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl extends CrudServiceImpl<Book, BookDto, Long> implements BookService {

    @Autowired
    private final BookRepo bookRepo;
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final CategoryRepo categoryRepo;

    @Autowired
    private AuditLogServiceImpl auditService;

    public BookServiceImpl(BookRepo bookRepo, UserRepo userRepo, CategoryRepo categoryRepo, BookRepo repository, ModelMapper modelMapper) {
        super(Book.class, BookDto.class);
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.bookRepo = bookRepo;
    }

    @Override
    public BookDto create(BookDto dto) {
        Category category = this.categoryRepo.findById(dto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Category.class.getSimpleName(), "id", dto.getCategory().getId()));
        User user = this.userRepo.findById(dto.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), "id", dto.getUser().getId()));
        Book book = this.modelMapper.map(dto, Book.class);
        book.setCategory(category);
        book.setUser(user);
        Book createdBook = this.repository.save(book);
        this.auditService.log("CREATE", Book.class.getSimpleName(), createdBook.getId(), "Anonymous63", 63L, null);
        return this.modelMapper.map(createdBook, BookDto.class);
    }


    @Override
    public CrudResponse findBookByCategoryId(Long id, Integer page, Integer size, String sortBy, String sortDir) {
        Category category = this.categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class.getSimpleName(), "id", id));
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Book> bookPage = this.bookRepo.findBookByCategory(category, pageable);
        List<Book> books = bookPage.getContent();
        List<BookDto> bookDtos = books.stream().map(book -> this.modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
        CrudResponse<BookDto> response = CrudResponseConverter.convertToResponse(bookDtos, bookPage);
        return response;
    }

    @Override
    public CrudResponse findBookByUserId(Long id, Integer page, Integer size, String sortBy, String sortDir) {
        User user = this.userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), "id", id));
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Book> bookPage = this.bookRepo.findBookByUser(user, pageable);
        List<Book> books = bookPage.getContent();
        List<BookDto> bookDtos = books.stream().map(book -> this.modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
        CrudResponse<BookDto> response = CrudResponseConverter.convertToResponse(bookDtos, bookPage);
        return response;
    }

    @Override
    public CrudResponse findBookByTitleContaining(String keyword, Integer page, Integer size, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Book> bookPage = this.bookRepo.findBookByTitleContaining(keyword, pageable);
        List<Book> books = bookPage.getContent();
        List<BookDto> bookDtos = books.stream().map(book -> this.modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
        CrudResponse<BookDto> response = CrudResponseConverter.convertToResponse(bookDtos, bookPage);
        return response;
    }

}
