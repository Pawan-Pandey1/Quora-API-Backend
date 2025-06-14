package com.Quora.Quora.Backedn.Api.controller;

import com.Quora.Quora.Backedn.Api.dto.QuestionRequest;
import com.Quora.Quora.Backedn.Api.dto.QuestionResponse;
import com.Quora.Quora.Backedn.Api.model.Question;
import com.Quora.Quora.Backedn.Api.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(
            @RequestBody QuestionRequest request) {
        // Create the question
        Question question = questionService.createQuestion(
                request.getUserId(),
                request.getTitle(),
                request.getBody(),
                request.getTopicTags());

        // Reload the question with topics to ensure topics are loaded
        Question savedQuestion = questionService.getQuestionWithTopics(question.getId());

        // Convert to DTO
        QuestionResponse response = mapToResponse(savedQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestionResponse>> searchQuestions(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String tag) {
        List<Question> questions = questionService.searchQuestions(text, tag);
        // Convert to DTOs
        List<QuestionResponse> responses = questions.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // Helper method to map Question entity to QuestionResponse DTO
    private QuestionResponse mapToResponse(Question question) {
        return new QuestionResponse(
                question.getId(),
                question.getTitle(),
                question.getBody(),
                question.getTopics().stream().map(t -> t.getName()).toList(),
                question.getCreatedAt(),
                question.getUser().getId()
        );
    }
}
