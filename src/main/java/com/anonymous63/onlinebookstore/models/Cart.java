package com.anonymous63.onlinebookstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Cart {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private int quantity;

    private double totalPrice;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

}
