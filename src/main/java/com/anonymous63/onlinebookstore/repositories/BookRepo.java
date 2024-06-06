package com.anonymous63.onlinebookstore.repositories;

import com.anonymous63.onlinebookstore.models.Book;
import com.anonymous63.onlinebookstore.models.Category;
import com.anonymous63.onlinebookstore.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    Page<Book> findBookByCategory(Category category, Pageable pageable);

    Page<Book> findBookByUser(User user, Pageable pageable);

    Page<Book> findBookByTitleContaining(String keyword, Pageable pageable);

}
