package com.example.quiz.requests;

import com.example.quiz.entities.Quiz;

import java.sql.Timestamp;

public record CreateQuizInput(
        String classId,
        String title,
        String metaTitle,
        String startsAt,
        String endsAt,
        String content
) {
    public Quiz toQuiz() {
        Quiz quiz = new Quiz();
        quiz.setClassId(classId);
        quiz.setTitle(title);
        quiz.setMetaTitle(metaTitle);
        quiz.setStartsAt(Timestamp.valueOf(startsAt));
        quiz.setEndsAt(Timestamp.valueOf(endsAt));
        quiz.setContent(content);
        return quiz;
    }
}
