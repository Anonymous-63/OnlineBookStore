package com.anonymous63.onlinebookstore.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String action;
    @Column(length = 50)
    private String entityName;
    private Long entityId;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;
    @Column(length = 255)
    private String details;


}
