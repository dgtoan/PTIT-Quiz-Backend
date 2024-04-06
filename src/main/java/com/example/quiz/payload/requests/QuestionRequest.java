package com.example.quiz.payload.requests;

import com.example.quiz.models.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {
    private String question;
    private Integer points;
    private List<QuestionOptionRequest> questionOptions;

    public Boolean checkInput() {
        return this.question != null &&
                this.points != null &&
                this.questionOptions != null;
    }

    public Question toQuestion() {
        Question question = new Question();
        question.setQuestion(this.question);
        question.setPoints(this.points);
        this.getQuestionOptions().forEach(questionOptionRequest -> {
            question.getQuestionOptions().add(questionOptionRequest.toQuestionOption());
        });
        return question;
    }
}
