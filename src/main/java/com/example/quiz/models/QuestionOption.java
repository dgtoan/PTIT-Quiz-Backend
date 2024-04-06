package com.example.quiz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "QUESTION_OPTION")
@NoArgsConstructor @Getter @Setter
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isCorrect;

    private String answer;

    public QuestionOption(String answer, Boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    @ManyToOne
    @JoinColumn(name = "quiz_question_id")
    private Question question;

}
