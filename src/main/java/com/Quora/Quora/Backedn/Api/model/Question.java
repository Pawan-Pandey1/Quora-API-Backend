package com.Quora.Quora.Backedn.Api.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @ManyToMany
    @JoinTable(
            name = "question_topics",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference("question-answers")
    @ToString.Exclude
    private List<Answer> answers = new ArrayList<>();

    @Column(nullable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-questions")
    private User user;
}
