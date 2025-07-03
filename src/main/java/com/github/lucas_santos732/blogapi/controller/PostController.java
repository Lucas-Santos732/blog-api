package com.github.lucas_santos732.blogapi.controller;

import com.github.lucas_santos732.blogapi.dto.commentDTO;
import com.github.lucas_santos732.blogapi.dto.postDTO;
import com.github.lucas_santos732.blogapi.entity.Comment;
import com.github.lucas_santos732.blogapi.entity.Post;
import com.github.lucas_santos732.blogapi.entity.User;
import com.github.lucas_santos732.blogapi.repository.CommentRepository;
import com.github.lucas_santos732.blogapi.repository.PostRepository;
import com.github.lucas_santos732.blogapi.repository.UserRepository;
import com.github.lucas_santos732.blogapi.service.CommentService;
import com.github.lucas_santos732.blogapi.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
public class PostController {
    PostService postService;
    CommentService commentService;

    public PostController(PostService postService,
                          CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody postDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDTO));
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAllPost());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> findPost(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPost(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody postDTO postDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(postDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePost(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(id));
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable int postId, @RequestBody commentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(postId, commentDTO));
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> findAllComment() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAllComment());
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Integer> deleteComment(@PathVariable int commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(commentId));
    }
}


