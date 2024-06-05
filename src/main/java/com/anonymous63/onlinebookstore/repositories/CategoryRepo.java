package com.anonymous63.onlinebookstore.repositories;

import com.anonymous63.onlinebookstore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
