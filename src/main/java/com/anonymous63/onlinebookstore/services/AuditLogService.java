package com.anonymous63.onlinebookstore.services;

import java.time.LocalDateTime;

public interface AuditLogService {

    void log(String action, String entityName, Long entityId, String username, Long userId, String details);
}
