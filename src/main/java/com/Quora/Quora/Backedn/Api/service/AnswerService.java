package com.Quora.Quora.Backedn.Api.service;

import com.Quora.Quora.Backedn.Api.exceptions.ResourceNotFoundException;
import com.Quora.Quora.Backedn.Api.model.Answer;
import com.Quora.Quora.Backedn.Api.model.Question;
import com.Quora.Quora.Backedn.Api.model.User;
import com.Quora.Quora.Backedn.Api.repository.AnswerRepository;
import com.Quora.Quora.Backedn.Api.repository.QuestionRepository;
import com.Quora.Quora.Backedn.Api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Answer createAnswer(UUID questionId, UUID userId, String text) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Answer answer = Answer.builder()
                .text(text)
                .question(question)
                .user(user)
                .createdAt(Instant.now())
                .build();

        return answerRepository.save(answer);
    }

    @Transactional
    public Answer editAnswer(UUID answerId, String newText) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        answer.setText(newText);
        // Optionally update timestamp: answer.setUpdatedAt(Instant.now());
        return answerRepository.save(answer);
    }
}
