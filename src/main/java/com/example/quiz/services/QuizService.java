package com.example.quiz.services;

import com.example.quiz.models.Quiz;
import com.example.quiz.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    public final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public List<Quiz> searchQuizzes(String title) {
        return quizRepository.findQuizzesByTitleContainingIgnoreCase(title);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    public Quiz updateQuizById(Long id, Quiz quiz) {
        Quiz existingQuiz = quizRepository.findById(id).orElse(null);
        if (existingQuiz == null) {
            return null;
        }
        existingQuiz.setTitle(quiz.getTitle());
        existingQuiz.setDescription(quiz.getDescription());
        existingQuiz.setQuestions(quiz.getQuestions());
        return quizRepository.save(existingQuiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}