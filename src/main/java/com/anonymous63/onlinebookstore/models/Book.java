package com.anonymous63.onlinebookstore.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String title;
    @Column(nullable = false, length = 50)
    private String author;
    @Column(length = 255)
    private String description;
    private String imageUrl;
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
}
