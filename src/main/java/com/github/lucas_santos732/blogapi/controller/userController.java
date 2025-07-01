package com.github.lucas_santos732.blogapi.controller;

import com.github.lucas_santos732.blogapi.entity.User;
import com.github.lucas_santos732.blogapi.repository.userRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class userController {
    userRepository userRepository;

    public userController(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/{id}")
    public Optional<User> findUser(@PathVariable UUID id) {
        return userRepository.findById(id);
    }
}
