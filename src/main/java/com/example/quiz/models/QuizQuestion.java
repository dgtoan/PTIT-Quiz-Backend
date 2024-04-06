package com.example.quiz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "QUIZ_QUESTION")
@NoArgsConstructor @Getter  @Setter
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String question;

    private Integer points;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "quizQuestion")
    private Set<QuestionAnswer> options = new HashSet<>();

    @OneToMany(mappedBy = "quizQuestion")
    private Set<QuestionAnswer> answers = new HashSet<>();
}
