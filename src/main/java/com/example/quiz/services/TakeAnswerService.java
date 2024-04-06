package com.example.quiz.services;

import com.example.quiz.models.TakeAnswer;
import org.springframework.stereotype.Service;
import com.example.quiz.repositories.TakeAnswerRepository;

import java.util.List;

@Service
public class TakeAnswerService {
    public final TakeAnswerRepository takeAnswerRepository;

    public TakeAnswerService(TakeAnswerRepository takeAnswerRepository) {
        this.takeAnswerRepository = takeAnswerRepository;
    }

    // get all take answers
    public List<TakeAnswer> getAllTakeAnswers() {
        return takeAnswerRepository.findAll();
    }


    // get take answer by id
    public TakeAnswer getTakeAnswerById(Long id) {
        return takeAnswerRepository.findById(id).orElse(null);
    }

    // update take answer by id
    public TakeAnswer updateTakeAnswer(Long id, TakeAnswer takeAnswer) {
        TakeAnswer existingTakeAnswer = takeAnswerRepository.findById(id).orElse(null);
        if (existingTakeAnswer == null) {
            return null;
        }
        existingTakeAnswer.setQuestionOption(takeAnswer.getQuestionOption());
        existingTakeAnswer.setQuestion(takeAnswer.getQuestion());
        existingTakeAnswer.setTake(takeAnswer.getTake());
        return takeAnswerRepository.save(existingTakeAnswer);
    }

}
