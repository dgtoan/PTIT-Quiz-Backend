package com.example.quiz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "QUESTION")
@NoArgsConstructor @Getter  @Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String question;

    private Integer points;

    public Question(String question, Integer points) {
        this.question = question;
        this.points = points;
    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionOption> questionOptions = new HashSet<>();
}
