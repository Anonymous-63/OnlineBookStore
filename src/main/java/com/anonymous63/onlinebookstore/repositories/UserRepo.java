package com.anonymous63.onlinebookstore.repositories;

import com.anonymous63.onlinebookstore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
