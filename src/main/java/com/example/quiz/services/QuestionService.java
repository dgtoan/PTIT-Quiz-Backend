package com.example.quiz.services;

import com.example.quiz.models.Question;
import com.example.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    public final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // get all questions
    public List<Question> getAllQuizQuestions() {
        return questionRepository.findAll();
    }

    // create question
    public Question createQuizQuestion(Question question) {
        return questionRepository.save(question);
    }

    // get question by id
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    // update question
    public Question updateQuizQuestion(Long id, Question question) {
        Question existingQuestion = questionRepository.findById(id).orElse(null);
        if (existingQuestion != null) {
            existingQuestion.setQuestion(question.getQuestion());
            existingQuestion.setPoints(question.getPoints());
            existingQuestion.setQuestionOptions(question.getQuestionOptions());
            return questionRepository.save(existingQuestion);
        } else {
            return null;
        }
    }

    // delete question
    public void deleteQuizQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
