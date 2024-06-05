package com.anonymous63.onlinebookstore.repositories;

import com.anonymous63.onlinebookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
