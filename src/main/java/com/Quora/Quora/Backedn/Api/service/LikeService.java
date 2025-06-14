package com.Quora.Quora.Backedn.Api.service;

import com.Quora.Quora.Backedn.Api.exceptions.ResourceNotFoundException;
import com.Quora.Quora.Backedn.Api.model.*;
import com.Quora.Quora.Backedn.Api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void likeQuestion(UUID questionId, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        Like like = Like.builder().user(user).question(question).build();
        likeRepository.save(like);
    }

    @Transactional
    public void likeAnswer(UUID answerId, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        Like like = Like.builder().user(user).answer(answer).build();
        likeRepository.save(like);
    }

    @Transactional
    public void likeComment(UUID commentId, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        Like like = Like.builder().user(user).comment(comment).build();
        likeRepository.save(like);
    }
}
