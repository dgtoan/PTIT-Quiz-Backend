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

    @OneToMany(mappedBy = "quiz")
    private Set<QuizQuestion> questions = new HashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<Take> takes = new HashSet<>();

    public Quiz(String title, String description, Timestamp startTime, Timestamp endTime) {
        this.title = title;
        this.description = description;
        this.startsAt = startTime;
        this.endsAt = endTime;
    }
}
