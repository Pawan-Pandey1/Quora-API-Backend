package com.Quora.Quora.Backedn.Api.dto;

import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
public class AnswerResponse {
    private UUID id;
    private String text;
    private Instant createdAt;
    private UUID userId;
    private UUID questionId;

    public AnswerResponse(UUID id, String text, Instant createdAt, UUID userId, UUID questionId) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.userId = userId;
        this.questionId = questionId;
    }
}
