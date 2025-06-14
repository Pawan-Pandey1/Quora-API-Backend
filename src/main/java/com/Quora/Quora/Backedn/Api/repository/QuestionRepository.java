package com.Quora.Quora.Backedn.Api.repository;

import com.Quora.Quora.Backedn.Api.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    // Search with topics
    @Query("SELECT DISTINCT q FROM Question q " +
            "LEFT JOIN FETCH q.topics t " +
            "WHERE (:text IS NULL OR LOWER(q.title) LIKE LOWER(CONCAT('%', :text, '%')) OR LOWER(q.body) LIKE LOWER(CONCAT('%', :text, '%'))) " +
            "AND (:tag IS NULL OR LOWER(t.name) = LOWER(:tag))")
    List<Question> search(@Param("text") String text, @Param("tag") String tag);

    // Get question with topics (for post-creation reload)
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.topics WHERE q.id = :id")
    Optional<Question> findByIdWithTopics(@Param("id") UUID id);
}
