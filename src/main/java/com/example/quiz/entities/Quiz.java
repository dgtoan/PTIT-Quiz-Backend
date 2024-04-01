package com.example.quiz.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quizs")
@NoArgsConstructor @Getter @Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String title;

    @Size(max = 120)
    private String description;

    private Timestamp startTime;

    private Timestamp endTime;

    @OneToMany(mappedBy = "quiz")
    private Set<QuizQuestion> questions = new HashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<Take> takes = new HashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionAnswer> answers = new HashSet<>();

    public Quiz(String title, String description, Timestamp startTime, Timestamp endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
