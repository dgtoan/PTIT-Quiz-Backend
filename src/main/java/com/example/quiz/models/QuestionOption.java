package com.example.quiz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "QUESTION_OPTION")
@NoArgsConstructor @Getter @Setter
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isCorrect;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "quiz_question_id")
    private Question question;
}
