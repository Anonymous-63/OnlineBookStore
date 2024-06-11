package com.anonymous63.onlinebookstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    private long id;
    private String name;
}
