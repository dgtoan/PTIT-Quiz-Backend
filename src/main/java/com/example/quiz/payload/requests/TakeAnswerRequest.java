package com.example.quiz.payload.requests;

import com.example.quiz.models.TakeAnswer;
import com.example.quiz.services.QuestionService;
import com.example.quiz.services.QuestionOptionService;
import com.example.quiz.services.TakeService;
import lombok.Data;

@Data
public class TakeAnswerRequest {
    private Long questionId;
    private Long questionOptionId;
    private Long takeId;
    private Boolean isCorrect;

    public boolean checkInput() {
        return questionId != null && questionOptionId != null && takeId != null && isCorrect != null;
    }

    public TakeAnswer toTakeAnswer(TakeService takeService, QuestionService questionService, QuestionOptionService questionOptionService) {
        TakeAnswer takeAnswer = new TakeAnswer();
        takeAnswer.setQuestion(questionService.getQuestionById(questionId));
        takeAnswer.setQuestionOption(questionOptionService.getQuestionOptionById(questionOptionId));
        takeAnswer.setTake(takeService.getTakeById(takeId));
        takeAnswer.setIsCorrect(isCorrect);
        return takeAnswer;
    }
}
