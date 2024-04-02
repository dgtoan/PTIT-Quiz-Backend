package com.example.quiz.payload.requests;

import lombok.Data;

@Data
public class AnswerRequest {
    private Boolean isCorrect;
    private String answer;
}
