package com.github.lucas_santos732.blogapi.service;

import com.github.lucas_santos732.blogapi.entity.User;
import com.github.lucas_santos732.blogapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUser(UUID id) {
        return userRepository.findById(id);
    }
}
