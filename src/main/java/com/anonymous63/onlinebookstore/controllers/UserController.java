package com.anonymous63.onlinebookstore.controllers;

import com.anonymous63.onlinebookstore.payloads.dtos.UserDto;
import com.anonymous63.onlinebookstore.payloads.response.ApiResponse;
import com.anonymous63.onlinebookstore.payloads.response.CrudResponse;
import com.anonymous63.onlinebookstore.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users/
    @GetMapping("/")
    public CrudResponse findAllUsers(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                    @RequestParam(value = "size", defaultValue = "100", required = false) int size,
                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {
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
        return this.userService.create(userDto);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        userDto.setId(id);
        return this.userService.update(userDto, id);
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok(new ApiResponse("User Deleted Successfully", true));
    }


}
