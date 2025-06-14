package com.Quora.Quora.Backedn.Api.service;

import com.Quora.Quora.Backedn.Api.exceptions.ResourceNotFoundException;
import com.Quora.Quora.Backedn.Api.model.Topic;
import com.Quora.Quora.Backedn.Api.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    /**
     * Creates a new topic with the given name.
     * If a topic with the same name already exists, throws IllegalArgumentException.
     */
    public Topic createTopic(String name) {
        if (topicRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Topic with name '" + name + "' already exists");
        }
        Topic topic = Topic.builder().name(name).build();
        return topicRepository.save(topic);
    }

    /**
     * Returns all topics in the database.
     */
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    /**
     * Finds a topic by name or throws ResourceNotFoundException if not found.
     */
    public Topic getTopicByName(String name) {
        return topicRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found: " + name));
    }
}
