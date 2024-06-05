package com.anonymous63.onlinebookstore.controllers;

import com.anonymous63.onlinebookstore.payloads.dtos.BookDto;
import com.anonymous63.onlinebookstore.payloads.response.ApiResponse;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;
import com.anonymous63.onlinebookstore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /api/books
    @GetMapping("/")
    public CrudResponse findAllBooks(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                     @RequestParam(value = "size", defaultValue = "100", required = false) int size,
                                     @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                     @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {
        return bookService.findAll(page, size, sortBy, sortDir);
    }

    // GET /api/books/{id}
    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    // POST /api/books
    @PostMapping("/")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.create(bookDto);
    }

    // PUT /api/books/{id}
    @PutMapping("/{id}")
    public BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable Long id) {
        bookDto.setId(id);
        return bookService.update(bookDto, id);
    }

    // DELETE /api/books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(new ApiResponse("Book Deleted Successfully", true), HttpStatus.OK);
    }

}
