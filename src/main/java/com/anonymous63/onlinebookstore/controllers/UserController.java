package com.anonymous63.onlinebookstore.controllers;

import com.anonymous63.onlinebookstore.configs.Constants;
import com.anonymous63.onlinebookstore.payloads.dtos.UserDto;
import com.anonymous63.onlinebookstore.payloads.response.ApiResponse;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;
import com.anonymous63.onlinebookstore.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users/
    @GetMapping("/")
    public CrudResponse findAllUsers(@RequestParam(value = "page", defaultValue = Constants.PAGE_NUMBER, required = false) int page,
                                     @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE, required = false) int size,
                                     @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                     @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {
        return this.userService.findAll(page, size, sortBy, sortDir);
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    // POST /api/users/
    @PostMapping("/")
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return this.userService.create(userDto);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        userDto.setId(id);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return this.userService.update(userDto, id);
    }

    // DELETE /api/users/{id}
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok(new ApiResponse("User Deleted Successfully", true));
    }


}
