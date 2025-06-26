package com.github.lucas_santos732.blogapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "title")
    String title;
    @Lob
    @Column(name = "content")
    String content;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true, foreignKey = @ForeignKey(name = "fk_author_user"))
    User authorId;
    @CreationTimestamp
    @Column(name = "published_at")
    Instant publishedAt;
    @UpdateTimestamp
    @Column(name = "update_at")
    Instant updateAt;

}
