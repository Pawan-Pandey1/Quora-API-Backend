package com.Quora.Quora.Backedn.Api.controller;

import com.Quora.Quora.Backedn.Api.dto.CommentRequest;
import com.Quora.Quora.Backedn.Api.dto.CommentResponse;
import com.Quora.Quora.Backedn.Api.model.Comment;
import com.Quora.Quora.Backedn.Api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/answers/{answerId}/comments")
    public ResponseEntity<CommentResponse> commentOnAnswer(
            @PathVariable UUID answerId,
            @RequestBody CommentRequest request) {
        Comment comment = commentService.commentOnAnswer(
                answerId,
                request.getUserId(),
                request.getText());
        CommentResponse response = mapToResponse(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/comments/{parentCommentId}/comments")
    public ResponseEntity<CommentResponse> commentOnComment(
            @PathVariable UUID parentCommentId,
            @RequestBody CommentRequest request) {
        Comment comment = commentService.commentOnComment(
                parentCommentId,
                request.getUserId(),
                request.getText());
        CommentResponse response = mapToResponse(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/answers/{answerId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByAnswer(@PathVariable UUID answerId) {
        List<Comment> comments = commentService.getCommentsByAnswer(answerId);
        List<CommentResponse> responses = comments.stream().map(this::mapToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/comments/{parentCommentId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByParentComment(@PathVariable UUID parentCommentId) {
        List<Comment> comments = commentService.getCommentsByParentComment(parentCommentId);
        List<CommentResponse> responses = comments.stream().map(this::mapToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    private CommentResponse mapToResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                comment.getUser().getId(),
                comment.getUser().getUsername()
        );
    }
}
