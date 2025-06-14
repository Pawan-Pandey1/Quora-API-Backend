package com.Quora.Quora.Backedn.Api.service;

import com.Quora.Quora.Backedn.Api.exceptions.ResourceNotFoundException;
import com.Quora.Quora.Backedn.Api.model.Answer;
import com.Quora.Quora.Backedn.Api.model.Comment;
import com.Quora.Quora.Backedn.Api.model.User;
import com.Quora.Quora.Backedn.Api.repository.AnswerRepository;
import com.Quora.Quora.Backedn.Api.repository.CommentRepository;
import com.Quora.Quora.Backedn.Api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment commentOnAnswer(UUID answerId, UUID userId, String text) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Comment comment = Comment.builder()
                .text(text)
                .createdAt(Instant.now())
                .user(user)
                .answer(answer)
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment commentOnComment(UUID parentCommentId, UUID userId, String text) {
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent comment not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Comment comment = Comment.builder()
                .text(text)
                .createdAt(Instant.now())
                .user(user)
                .parentComment(parentComment)
                .build();

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByAnswer(UUID answerId) {
        return commentRepository.findByAnswerId(answerId);
    }

    public List<Comment> getCommentsByParentComment(UUID parentCommentId) {
        return commentRepository.findByParentCommentId(parentCommentId);
    }
}
