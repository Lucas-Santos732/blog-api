package com.github.lucas_santos732.blogapi.repository;

import com.github.lucas_santos732.blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postRepository extends JpaRepository<Post, Integer> {
}
