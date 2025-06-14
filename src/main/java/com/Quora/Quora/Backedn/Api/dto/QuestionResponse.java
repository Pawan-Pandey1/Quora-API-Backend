package com.Quora.Quora.Backedn.Api.dto;

import lombok.Data;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class QuestionResponse {
    private UUID id;
    private String title;
    private String body;
    private List<String> topicNames;
    private Instant createdAt;
    private UUID userId;

    public QuestionResponse(UUID id, String title, String body, List<String> topicNames, Instant createdAt, UUID userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.topicNames = topicNames;
        this.createdAt = createdAt;
        this.userId = userId;
    }
}
