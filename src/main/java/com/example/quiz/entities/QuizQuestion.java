package com.example.quiz.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz_questions")
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
    private Set<TakeAnswer> answers = new HashSet<>();
}
