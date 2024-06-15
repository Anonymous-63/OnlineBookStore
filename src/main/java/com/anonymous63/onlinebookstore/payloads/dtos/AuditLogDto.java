package com.anonymous63.onlinebookstore.payloads.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuditLogDto {

    private Long id;

    @NotEmpty(message = "Action cannot be empty")
    @Size(max = 10, message = "Action must be less than 10 characters")
    private String action;

    @Size(max = 20, message = "Entity Name must be less than 20 characters")
    private String entityName;

    @Size(max = 20, message = "Entity ID must be less than 20 characters")
    private Long entityId;

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "User ID cannot be empty")
    private Long userId;

    @NotEmpty(message = "createdAt cannot be empty")
    private String createdAt;

    @Size(max = 255, message = "Details must be less than 255 characters")
    private String details;
}

