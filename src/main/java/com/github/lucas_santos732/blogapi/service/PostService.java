package com.github.lucas_santos732.blogapi.service;

import com.github.lucas_santos732.blogapi.dto.postDTO;
import com.github.lucas_santos732.blogapi.entity.Post;
import com.github.lucas_santos732.blogapi.entity.User;
import com.github.lucas_santos732.blogapi.repository.CommentRepository;
import com.github.lucas_santos732.blogapi.repository.PostRepository;
import com.github.lucas_santos732.blogapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(postDTO postDTO) {
        User user = userRepository.findById(postDTO.authorId()).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        Post post = new Post(
                null,
                postDTO.title(),
                postDTO.content(),
                user,
                Instant.now(),
                Instant.now()
        );
        return postRepository.save(post);
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Optional<Post> findPost(int id) {
        return postRepository.findById(id);
    }

    public Post updatePost(postDTO postDTO, int id) {
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
            return postRepository.save(post);
        }
        return null;
    }

    public int deletePost(int id) {
        postRepository.deleteById(id);
        return id;
    }
}
