package com.anonymous63.onlinebookstore.payloads.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuditLogDto {

    private Long id;

    @NotEmpty(message = "Action cannot be empty")
    private String action;

    private String entityName;

    private Long entityId;

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "User ID cannot be empty")
    private Long userId;

    @NotEmpty(message = "Timestamp cannot be empty")
    private String timestamp;

    @Size(max = 255, message = "Details must be less than 255 characters")
    private String details;
}

