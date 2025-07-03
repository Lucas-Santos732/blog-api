package com.github.lucas_santos732.blogapi.service;

import com.github.lucas_santos732.blogapi.dto.commentDTO;
import com.github.lucas_santos732.blogapi.entity.Comment;
import com.github.lucas_santos732.blogapi.entity.Post;
import com.github.lucas_santos732.blogapi.entity.User;
import com.github.lucas_santos732.blogapi.repository.CommentRepository;
import com.github.lucas_santos732.blogapi.repository.PostRepository;
import com.github.lucas_santos732.blogapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(UserRepository userRepository,
                          PostRepository postRepository,
                          CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Comment createComment(int postId, commentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post não encontrado"));
        User user = userRepository.findById(commentDTO.userId()).orElseThrow(() -> new RuntimeException("user não encontrado"));

        Comment comment = new Comment(
                null,
                commentDTO.content(),
                post,
                user,
                Instant.now()
        );

        return commentRepository.save(comment);

    }

    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    public int deleteComment(int id) {
        commentRepository.deleteById(id);
        return id;
    }
}
