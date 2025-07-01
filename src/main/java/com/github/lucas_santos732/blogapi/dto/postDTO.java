package com.github.lucas_santos732.blogapi.dto;

import java.util.UUID;

public record postDTO(Integer id, String title, String content, UUID authorId) {
}
