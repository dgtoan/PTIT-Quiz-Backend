package com.example.quiz.payload.requests;

import com.example.quiz.models.Take;
import com.example.quiz.services.QuizService;
import com.example.quiz.services.UserService;
import lombok.Data;

@Data
public class TakeRequest {
    private Long quizId;
    private Long userId;
    private Integer score;

    public boolean checkInput() {
        return quizId != null && userId != null && score != null;
    }

    public Take toTake(QuizService quizService, UserService userService) {
        Take take = new Take();
        take.setQuiz(quizService.getQuizById(quizId));
        take.setUser(userService.getUserById(userId));
        take.setScore(score);
        return take;
    }
}
