package com.Quora.Quora.Backedn.Api.dto;

import java.util.UUID;

public class CommentRequest {
    private UUID userId;
    private String text;

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
