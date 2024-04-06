package com.example.quiz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "QUIZ")
@NoArgsConstructor @Getter @Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String title;

    @Size(max = 120)
    private String description;

    private Timestamp startsAt;

    private Timestamp endsAt;

    public Quiz(String title, String description, Timestamp startsAt, Timestamp endsAt) {
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "quiz_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Take> takes = new HashSet<>();
}
