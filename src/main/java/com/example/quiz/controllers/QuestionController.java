package com.example.quiz.controllers;

import com.example.quiz.models.Question;
import com.example.quiz.payload.requests.QuestionRequest;
import com.example.quiz.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/questions")
public class QuestionController {
    public QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // get all questions
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuizQuestions());
    }

    // create new question
    @PostMapping("")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> createQuestion(@RequestBody QuestionRequest questionRequest) {
        if (!questionRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Question, points, and questionOptions are required.");
        }

        return ResponseEntity.ok(questionService.createQuizQuestion(questionRequest.toQuestion()));
    }

    // get question by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    // update question by id
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> updateQuestionById(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        if (!questionRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Question, points, and questionOptions are required.");
        }

        return ResponseEntity.ok(questionService.updateQuizQuestion(id, questionRequest.toQuestion()));
    }

    // delete question by id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> deleteQuestionById(@PathVariable Long id) {
        questionService.deleteQuizQuestion(id);
        return ResponseEntity.ok("Question deleted successfully.");
    }
}
