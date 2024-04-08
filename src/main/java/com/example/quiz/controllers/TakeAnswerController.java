package com.example.quiz.controllers;

import com.example.quiz.exeption.ResourceNotFoundException;
import com.example.quiz.models.Question;
import com.example.quiz.models.QuestionOption;
import com.example.quiz.models.Take;
import com.example.quiz.models.TakeAnswer;
import com.example.quiz.repositories.QuestionOptionRepository;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.repositories.TakeAnswerRepository;
import com.example.quiz.repositories.TakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class TakeAnswerController {
    @Autowired
    TakeAnswerRepository takeAnswerRepository;

    @Autowired
    TakeRepository takeRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionOptionRepository questionOptionRepository;

    // get all take answers by take id
    @GetMapping("/takes/{id}/take-answers")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<TakeAnswer>> getTakeAnswersByTakeId(@PathVariable Long id) {
        if (!takeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Take not found with id: " + id);
        }

        List<TakeAnswer> takeAnswers = takeAnswerRepository.findByTakeId(id);

        return ResponseEntity.ok(takeAnswers);
    }

    // get all take answers by question id
    @GetMapping("/questions/{id}/take-answers")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<TakeAnswer>> getTakeAnswersByQuestionId(@PathVariable Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }

        List<TakeAnswer> takeAnswers = takeAnswerRepository.findByQuestionId(id);

        return ResponseEntity.ok(takeAnswers);
    }

    // get take answer by id
    @GetMapping("/take-answers/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<TakeAnswer> getTakeAnswerById(@PathVariable Long id) {
        TakeAnswer takeAnswer = takeAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Take Answer not found with id: " + id));

        return ResponseEntity.ok(takeAnswer);
    }

    // create new take answer by take id and question id and question option id
    @PostMapping("/takes/{takeId}/questions/{questionId}/options/{optionId}/take-answers")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<TakeAnswer> createTakeAnswerByTakeIdAndQuestionIdAndOptionId(
            @PathVariable Long takeId,
            @PathVariable Long questionId,
            @PathVariable Long optionId
    ) {
        Take _take = takeRepository.findById(takeId)
                .orElseThrow(() -> new ResourceNotFoundException("Take not found with id: " + takeId));
        Question _question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + questionId));
        QuestionOption _questionOption = questionOptionRepository.findById(optionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question Option not found with id: " + optionId));

        TakeAnswer _takeAnswer = new TakeAnswer();

        _takeAnswer.setTake(_take);
        _takeAnswer.setQuestion(_question);
        _takeAnswer.setQuestionOption(_questionOption);

        return ResponseEntity.ok(takeAnswerRepository.save(_takeAnswer));
    }

    // update take answer by id
    @PutMapping("/take-answers/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<TakeAnswer> updateTakeAnswer(@PathVariable Long id) {
        TakeAnswer _takeAnswer = takeAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Take Answer not found with id: " + id));

        return ResponseEntity.ok(takeAnswerRepository.save(_takeAnswer));
    }

    // delete take answer by id
    @DeleteMapping("/take-answers/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<String> deleteTakeAnswer(@PathVariable Long id) {
        takeAnswerRepository.deleteById(id);

        return ResponseEntity.ok("Take Answer deleted successfully.");
    }

    // delete take answers by take id
    @DeleteMapping("/takes/{id}/take-answers")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<String> deleteTakeAnswersByTakeId(@PathVariable Long id) {
        if (!takeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Take not found with id: " + id);
        }

        takeAnswerRepository.deleteByTakeId(id);

        return ResponseEntity.ok("Take Answers deleted successfully.");
    }

    // delete take answers by question id
    @DeleteMapping("/questions/{id}/take-answers")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> deleteTakeAnswersByQuestionId(@PathVariable Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }

        takeAnswerRepository.deleteByQuestionId(id);

        return ResponseEntity.ok("Take Answers deleted successfully.");
    }
}
