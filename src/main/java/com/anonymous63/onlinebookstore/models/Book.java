package com.anonymous63.onlinebookstore.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    private String description;

    private String imageName;

    @Column(nullable = false, length = 50)
    private String publisher;

    @Column(nullable = false, length = 50)
    private String publicationDate;

    @Column(nullable = false, length = 20)
    private String language;

    @Column(nullable = false)
    private int pages;

    @Column(nullable = false, length = 20)
    private double listPrice;

    @Column(nullable = false, length = 20)
    private double ourPrice;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
