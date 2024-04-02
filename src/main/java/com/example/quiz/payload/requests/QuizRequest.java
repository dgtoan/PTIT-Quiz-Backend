package com.example.quiz.payload.requests;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class QuizRequest {
    private String title;
    private String description;
    private Timestamp startsAt;
    private Timestamp endsAt;
    private List<QuestionRequest> questions;
}