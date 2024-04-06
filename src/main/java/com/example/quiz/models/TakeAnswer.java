package com.example.quiz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TAKE_ANSWER")
@NoArgsConstructor @Getter @Setter
public class TakeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "question_option_id")
    private QuestionOption questionOption;

    @ManyToOne
    @JoinColumn(name = "take_id")
    private Take take;
}
