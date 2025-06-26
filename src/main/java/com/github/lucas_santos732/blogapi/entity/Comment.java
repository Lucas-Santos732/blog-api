package com.github.lucas_santos732.blogapi.entity;

import java.time.Instant;

public class Comment {
    Integer id;
    String content;
    Post postId;
    User userId;
    String authorName;
    Instant CreatedAt;
}
