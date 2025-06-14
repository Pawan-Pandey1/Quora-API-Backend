package com.Quora.Quora.Backedn.Api.controller;

import com.Quora.Quora.Backedn.Api.dto.TopicRequest;
import com.Quora.Quora.Backedn.Api.model.Topic;
import com.Quora.Quora.Backedn.Api.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody TopicRequest request) {
        Topic topic = topicService.createTopic(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }
}
