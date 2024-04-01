package com.example.quiz.payload.requests;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class QuizRequest {
    private String title;
    private String description;
    private Timestamp startsAt;
    private Timestamp endsAt;
}