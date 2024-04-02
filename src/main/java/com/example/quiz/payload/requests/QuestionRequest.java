package com.example.quiz.payload.requests;

import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {
    private String question;
    private Integer points;
    private List<AnswerRequest> answers;
}
