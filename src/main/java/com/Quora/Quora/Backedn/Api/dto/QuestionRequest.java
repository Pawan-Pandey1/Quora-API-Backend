package com.Quora.Quora.Backedn.Api.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class QuestionRequest {
    private UUID userId;
    private String title;
    private String body;
    private List<String> topicTags;
}
