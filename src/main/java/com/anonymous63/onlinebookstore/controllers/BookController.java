package com.anonymous63.onlinebookstore.controllers;

import com.anonymous63.onlinebookstore.configs.Constants;
import com.anonymous63.onlinebookstore.payloads.dtos.BookDto;
import com.anonymous63.onlinebookstore.payloads.response.ApiResponse;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;
import com.anonymous63.onlinebookstore.services.BookService;
import com.anonymous63.onlinebookstore.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String imageDir;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /api/books
    @GetMapping("/")
    public CrudResponse findAllBooks(@RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER, required = false) int page,
                                     @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE, required = false) int size,
                                     @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                     @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {
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

    // GET /api/books/category/{id}
    @GetMapping("/category/{id}")
    public ResponseEntity<CrudResponse> findBookByCategoryId(@PathVariable Long id,
                                                             @RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER, required = false) int page,
                                                             @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE, required = false) int size,
                                                             @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                                             @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {
        CrudResponse books = this.bookService.findBookByCategoryId(id, page, size, sortBy, sortDir);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // GET /api/books/user/{id}
    @GetMapping("/user/{id}")
    public ResponseEntity<CrudResponse> findBookByUserId(@PathVariable Long id,
                                                         @RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER, required = false) int page,
                                                         @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE, required = false) int size,
                                                         @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                                         @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {
        CrudResponse books = this.bookService.findBookByUserId(id, page, size, sortBy, sortDir);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // GET /api/books/search
    @GetMapping("/search")
    public ResponseEntity<CrudResponse> findBookByTitleContaining(@RequestParam String keyword,
                                                                  @RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER, required = false) int page,
                                                                  @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE, required = false) int size,
                                                                  @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                                                  @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {
        CrudResponse books = this.bookService.findBookByTitleContaining(keyword, page, size, sortBy, sortDir);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/image/upload/{id}")
    public ResponseEntity<BookDto> uploadBookImage(@RequestParam MultipartFile image, @PathVariable Long id) throws IOException {
        BookDto bookDto = this.bookService.findById(id);
        String fileName = this.fileService.uploadImage(imageDir, image);
        bookDto.setImageName(fileName);
        this.bookService.update(bookDto, id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadBookImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        try (InputStream resource = this.fileService.getResource(imageDir, imageName)) {
            StreamUtils.copy(resource, response.getOutputStream());
        }
    }


}
