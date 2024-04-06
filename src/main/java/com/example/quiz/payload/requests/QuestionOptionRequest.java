package com.example.quiz.payload.requests;

import com.example.quiz.models.QuestionOption;
import lombok.Data;

@Data
public class QuestionOptionRequest {
    private String answer;
    private Boolean isCorrect;

    public Boolean checkInput() {
        return this.answer != null &&
                this.isCorrect != null;
    }

    public QuestionOption toQuestionOption() {
        QuestionOption questionOption = new QuestionOption();
        questionOption.setAnswer(this.answer);
        questionOption.setIsCorrect(this.isCorrect);
        return questionOption;
    }
}
