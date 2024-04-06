package com.example.quiz.services;

import com.example.quiz.models.QuestionOption;
import com.example.quiz.repositories.QuestionOptionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionOptionService {
    public final QuestionOptionRepository questionOptionRepository;

    public QuestionOptionService(QuestionOptionRepository questionOptionRepository) {
        this.questionOptionRepository = questionOptionRepository;
    }

    // get question options by id
    public QuestionOption getQuestionOptionById(Long id) {
        return questionOptionRepository.findById(id).orElse(null);
    }
}
