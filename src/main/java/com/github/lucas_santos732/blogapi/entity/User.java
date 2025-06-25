package com.github.lucas_santos732.blogapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column(name = "username", unique = true, length = 40)
    String username;
    @Column(name = "email", unique = true, length = 100)
    String email;
    @Column(name = "password")
    String passwordHash;
    @CreationTimestamp
    @Column(name = "created_at")
    Instant CreatedAt;
}
