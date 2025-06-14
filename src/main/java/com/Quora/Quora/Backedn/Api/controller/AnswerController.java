package com.Quora.Quora.Backedn.Api.controller;

import com.Quora.Quora.Backedn.Api.dto.AnswerRequest;
import com.Quora.Quora.Backedn.Api.dto.AnswerResponse;
import com.Quora.Quora.Backedn.Api.model.Answer;
import com.Quora.Quora.Backedn.Api.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<AnswerResponse> postAnswer(
            @PathVariable UUID questionId,
            @RequestBody AnswerRequest request) {
        Answer answer = answerService.createAnswer(
                questionId,
                request.getUserId(),
                request.getText()
        );
        AnswerResponse response = new AnswerResponse(
                answer.getId(),
                answer.getText(),
                answer.getCreatedAt(),
                answer.getUser().getId(),
                answer.getQuestion().getId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/answers/{answerId}")
    public ResponseEntity<AnswerResponse> editAnswer(
            @PathVariable UUID answerId,
            @RequestBody AnswerRequest request) {
        Answer updatedAnswer = answerService.editAnswer(answerId, request.getText());
        AnswerResponse response = new AnswerResponse(
                updatedAnswer.getId(),
                updatedAnswer.getText(),
                updatedAnswer.getCreatedAt(),
                updatedAnswer.getUser().getId(),
                updatedAnswer.getQuestion().getId()
        );
        return ResponseEntity.ok(response);
    }
}
