package com.example.quiz.controllers;

import com.example.quiz.exeption.ResourceNotFoundException;
import com.example.quiz.models.QuestionOption;
import com.example.quiz.repositories.QuestionOptionRepository;
import com.example.quiz.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class QuestionOptionController {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionOptionRepository questionOptionRepository;

    // get question options by question id
    @GetMapping("/questions/{id}/options")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<QuestionOption>> getQuestionOptionsByQuestionId(@PathVariable Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }
        List<QuestionOption> questionOptions = questionOptionRepository.findByQuestionId(id);

        return ResponseEntity.ok(questionOptions);
    }

    // get question option by id
    @GetMapping("/options/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<QuestionOption> getQuestionOptionById(@PathVariable Long id) {
        QuestionOption questionOption = questionOptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question Option not found with id: " + id));

        return ResponseEntity.ok(questionOption);
    }

    // create new question option
    @PostMapping("/questions/{id}/options")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<QuestionOption> createQuestionOption(@PathVariable Long id, @RequestBody QuestionOption questionOption) {
        QuestionOption _questionOption = questionRepository.findById(id).map(question -> {
            questionOption.setQuestion(question);
            return questionOptionRepository.save(questionOption);
        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));

        return ResponseEntity.ok(_questionOption);
    }

    // update question option by id
    @PutMapping("/options/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<QuestionOption> updateQuestionOption(@PathVariable Long id, @RequestBody QuestionOption questionOption) {
        QuestionOption _questionOption = questionOptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question Option not found with id: " + id));

        _questionOption.setAnswer(questionOption.getAnswer());
        _questionOption.setIsCorrect(questionOption.getIsCorrect());

        return ResponseEntity.ok(questionOptionRepository.save(_questionOption));
    }

    // delete question option by id
    @DeleteMapping("/options/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> deleteQuestionOption(@PathVariable Long id) {
        questionOptionRepository.deleteById(id);

        return ResponseEntity.ok("Question Option deleted successfully.");
    }

    // remove question option from question by question id and question option id
    @DeleteMapping("/questions/{questionId}/options/{optionId}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> removeQuestionOptionFromQuestion(@PathVariable Long questionId, @PathVariable Long optionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id: " + questionId);
        }

        QuestionOption questionOption = questionOptionRepository.findById(optionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question Option not found with id: " + optionId));

        questionOptionRepository.delete(questionOption);

        return ResponseEntity.ok("Question Option deleted successfully.");
    }


}
