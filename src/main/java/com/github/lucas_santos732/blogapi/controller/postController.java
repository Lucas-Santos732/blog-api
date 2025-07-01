package com.github.lucas_santos732.blogapi.controller;

import com.github.lucas_santos732.blogapi.dto.commentDTO;
import com.github.lucas_santos732.blogapi.dto.postDTO;
import com.github.lucas_santos732.blogapi.entity.Comment;
import com.github.lucas_santos732.blogapi.entity.Post;
import com.github.lucas_santos732.blogapi.entity.User;
import com.github.lucas_santos732.blogapi.repository.commentRepository;
import com.github.lucas_santos732.blogapi.repository.postRepository;
import com.github.lucas_santos732.blogapi.repository.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
public class postController {
    postRepository postRepository;
    userRepository userRepository;
    commentRepository commentRepository;

    public postController(postRepository postRepository,
                          userRepository userRepository,
                          commentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody postDTO postDTO) {
        User user = userRepository.findById(postDTO.authorId()).orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Post post = new Post();
        post.setTitle(postDTO.title());
        post.setContent(postDTO.content());
        post.setAuthorId(user);

        Post savedPost = postRepository.save(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping
    public List<Post> findPost() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> findUser(@PathVariable int id) {
        return postRepository.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody postDTO postDTO) {
        Optional<Post> postEntity = postRepository.findById(id);

        if (postEntity.isPresent()) {
            var post = postEntity.get();
            if (postDTO.authorId() != null) {
                User user = userRepository.findById(postDTO.authorId()).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
                post.setAuthorId(user);
            }

            if (postDTO.title() != null) {
                post.setTitle(postDTO.title());
            }
            if (postDTO.content() != null) {
                post.setContent(postDTO.content());
            }

            postRepository.save(post);

            return ResponseEntity.ok(post);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Integer> deletePost(@PathVariable int id) {
        postRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/{postId}/comments")
    ResponseEntity<Comment> createComment(@PathVariable int postId, @RequestBody commentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post não encontrado"));
        User user = userRepository.findById(commentDTO.userId()).orElseThrow(() -> new RuntimeException("user não encontrado"));
        Comment comment = new Comment();
        comment.setContent(commentDTO.content());
        comment.setPostId(post);
        comment.setUserId(user);

        Comment savedComment = commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);

    }

    @GetMapping("/{postId}/comments")
    public List<Comment> findComment() {
        return commentRepository.findAll();
    }

    @DeleteMapping("/comments/{commentId}")
    ResponseEntity<Integer> deleteComment(@PathVariable int commentId) {
        commentRepository.deleteById(commentId);
        return ResponseEntity.ok(commentId);
    }
}


