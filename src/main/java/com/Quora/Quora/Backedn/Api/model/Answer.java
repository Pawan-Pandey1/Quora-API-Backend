package com.Quora.Quora.Backedn.Api.model;

// Answer.java
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference("question-answers")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-answers")
    private User user;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    @JsonManagedReference("answer-comments")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    private Instant createdAt;
}
