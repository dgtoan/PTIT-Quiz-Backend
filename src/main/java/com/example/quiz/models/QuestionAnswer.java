package com.example.quiz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question_answers")
@NoArgsConstructor @Getter @Setter
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isCorrect;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "quiz_question_id")
    private QuizQuestion quizQuestion;

    @OneToMany(mappedBy = "questionAnswer")
    private Set<TakeAnswer> userAnswers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public QuestionAnswer(String answer, Boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
}
