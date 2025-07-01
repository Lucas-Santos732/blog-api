package com.github.lucas_santos732.blogapi.repository;

import com.github.lucas_santos732.blogapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface commentRepository extends JpaRepository<Comment, Integer> {
}
