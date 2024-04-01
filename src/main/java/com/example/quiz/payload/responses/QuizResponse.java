package com.example.quiz.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class QuizResponse {
    private Long id;
    private String title;
    private String description;
    private String startsAt;
    private String endsAt;
}