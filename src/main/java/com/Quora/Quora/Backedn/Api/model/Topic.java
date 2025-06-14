package com.Quora.Quora.Backedn.Api.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;

@Entity
@Table(name = "topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Add this
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include // Include only ID in equals/hashCode
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "topics")
    @JsonIgnore
    @ToString.Exclude // Prevent infinite loops in toString()
    private Set<Question> questions = new HashSet<>();
}
