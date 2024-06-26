package com.anonymous63.onlinebookstore.payloads.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only alphabets and spaces")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "First name is required")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name must contain only alphabets and spaces")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Last name must contain only alphabets and spaces")
    private String lastName;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    private boolean enabled;

    private Set<RoleDto> roles = new HashSet<>();

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
