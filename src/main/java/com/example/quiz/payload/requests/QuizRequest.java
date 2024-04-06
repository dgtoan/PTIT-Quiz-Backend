package com.example.quiz.payload.requests;

import com.example.quiz.models.Quiz;
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
    private String titleSearch;

    public Boolean checkInput() {
        return this.title != null &&
                this.description != null &&
                this.startsAt != null &&
                this.endsAt != null &&
                this.questions != null;
    }

    public Quiz toQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTitle(this.title);
        quiz.setDescription(this.description);
        quiz.setStartsAt(this.startsAt);
        quiz.setEndsAt(this.endsAt);
        this.getQuestions().forEach(questionRequest -> {
            quiz.getQuestions().add(questionRequest.toQuestion());
        });
        return quiz;
    }
}