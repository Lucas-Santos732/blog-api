package com.github.lucas_santos732.blogapi.dto;

import java.util.UUID;

public record commentDTO(String content, Integer postId, UUID userId) {
}
