package com.Quora.Quora.Backedn.Api.service;

import com.Quora.Quora.Backedn.Api.exceptions.ResourceNotFoundException;
import com.Quora.Quora.Backedn.Api.model.Question;
import com.Quora.Quora.Backedn.Api.model.Topic;
import com.Quora.Quora.Backedn.Api.model.User;
import com.Quora.Quora.Backedn.Api.repository.QuestionRepository;
import com.Quora.Quora.Backedn.Api.repository.TopicRepository;
import com.Quora.Quora.Backedn.Api.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public Question createQuestion(UUID userId, String title, String body, List<String> topicNames) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Set<Topic> topics = topicNames.stream()
                .map(name -> {
                    Topic topic = topicRepository.findByName(name)
                            .orElseGet(() -> topicRepository.save(Topic.builder().name(name).build()));
                    if (topic.getQuestions() == null) {
                        topic.setQuestions(new HashSet<>());
                    }
                    return topic;
                })
                .collect(Collectors.toSet());

        Question question = Question.builder()
                .title(title)
                .body(body)
                .user(user)
                .topics(topics)
                .createdAt(Instant.now())
                .build();

        topics.forEach(topic -> topic.getQuestions().add(question));
        Question savedQuestion = questionRepository.save(question);

        // Return reloaded question with topics
        return questionRepository.findByIdWithTopics(savedQuestion.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found after creation"));
    }

    @Transactional(readOnly = true)
    public List<Question> searchQuestions(String text, String tag) {
        return questionRepository.search(
                text != null ? text.toLowerCase() : null,
                tag != null ? tag.toLowerCase() : null
        );
    }

    @Transactional(readOnly = true)
    public Question getQuestionWithTopics(UUID questionId) {
        return questionRepository.findByIdWithTopics(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
    }
}
