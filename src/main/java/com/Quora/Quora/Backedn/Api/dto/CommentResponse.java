package com.Quora.Quora.Backedn.Api.dto;

import java.time.Instant;
import java.util.UUID;

public class CommentResponse {
    private UUID id;
    private String text;
    private Instant createdAt;
    private UUID userId;
    private String username;

    public CommentResponse(UUID id, String text, Instant createdAt, UUID userId, String username) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.userId = userId;
        this.username = username;
    }

    // Getters and setters (or use Lombok @Data)
    public UUID getId() { return id; }
    public String getText() { return text; }
    public Instant getCreatedAt() { return createdAt; }
    public UUID getUserId() { return userId; }
    public String getUsername() { return username; }
}
