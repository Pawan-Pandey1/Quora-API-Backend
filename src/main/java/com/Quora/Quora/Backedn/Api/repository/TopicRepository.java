package com.Quora.Quora.Backedn.Api.repository;

import com.Quora.Quora.Backedn.Api.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
    Optional<Topic> findByName(String name);
}
