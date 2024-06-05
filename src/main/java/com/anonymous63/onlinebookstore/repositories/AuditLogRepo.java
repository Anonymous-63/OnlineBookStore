package com.anonymous63.onlinebookstore.repositories;

import com.anonymous63.onlinebookstore.models.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepo extends JpaRepository<AuditLog, Long> {
}
