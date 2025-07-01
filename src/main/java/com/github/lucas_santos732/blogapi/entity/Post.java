package com.github.lucas_santos732.blogapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
@Entity
@Data
@Table(name = "posts")
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "title")
    String title;
    @Column(name = "content")
    String content;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, foreignKey = @ForeignKey(name = "fk_post_user"))
    User authorId;
    @CreationTimestamp
    @Column(name = "published_at")
    Instant publishedAt;
    @UpdateTimestamp
    @Column(name = "update_at")
    Instant updateAt;

}
