package com.anonymous63.onlinebookstore.repositories;

import com.anonymous63.onlinebookstore.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
