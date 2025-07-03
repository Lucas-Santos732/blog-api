package com.github.lucas_santos732.blogapi.controller;

import com.github.lucas_santos732.blogapi.entity.User;
import com.github.lucas_santos732.blogapi.repository.UserRepository;
import com.github.lucas_santos732.blogapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(id));
    }
}
