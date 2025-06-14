package com.Quora.Quora.Backedn.Api.controller;

import com.Quora.Quora.Backedn.Api.dto.LikeRequest;
import com.Quora.Quora.Backedn.Api.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/questions/{questionId}/likes")
    public ResponseEntity<Map<String, String>> likeQuestion(
            @PathVariable UUID questionId,
            @RequestBody LikeRequest request) {
        likeService.likeQuestion(questionId, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Question liked successfully"));
    }

    @PostMapping("/answers/{answerId}/likes")
    public ResponseEntity<Map<String, String>> likeAnswer(
            @PathVariable UUID answerId,
            @RequestBody LikeRequest request) {
        likeService.likeAnswer(answerId, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Answer liked successfully"));
    }

    @PostMapping("/comments/{commentId}/likes")
    public ResponseEntity<Map<String, String>> likeComment(
            @PathVariable UUID commentId,
            @RequestBody LikeRequest request) {
        likeService.likeComment(commentId, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Comment liked successfully"));
    }
}
