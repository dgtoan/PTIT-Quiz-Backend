package com.example.quiz.controllers;

import com.example.quiz.payload.requests.TakeAnswerRequest;
import com.example.quiz.services.QuestionOptionService;
import com.example.quiz.services.QuestionService;
import com.example.quiz.services.TakeAnswerService;
import com.example.quiz.services.TakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/take-answers")
public class TakeAnswerController {
    public TakeAnswerService takeAnswerService;
    public TakeService takeService;
    public QuestionService questionService;
    public QuestionOptionService questionOptionService;

    public TakeAnswerController(TakeAnswerService takeAnswerService, TakeService takeService, QuestionService questionService, QuestionOptionService questionOptionService) {
        this.takeAnswerService = takeAnswerService;
        this.takeService = takeService;
        this.questionService = questionService;
        this.questionOptionService = questionOptionService;
    }

    // get all take answers
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<?> getAllTakeAnswers() {
        return ResponseEntity.ok(takeAnswerService.getAllTakeAnswers());
    }

    // get take answer by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<?> getTakeAnswerById(@PathVariable Long id) {
        return ResponseEntity.ok(takeAnswerService.getTakeAnswerById(id));
    }

    // update take answer by id
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> updateTakeAnswerById(@PathVariable Long id, @RequestBody TakeAnswerRequest takeAnswerRequest) {
        if (!takeAnswerRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Take, question, and questionOption are required.");
        }
        return ResponseEntity.ok(takeAnswerService.updateTakeAnswer(id, takeAnswerRequest.toTakeAnswer(takeService, questionService, questionOptionService)));
    }
}
