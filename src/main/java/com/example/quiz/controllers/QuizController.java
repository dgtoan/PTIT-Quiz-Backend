package com.example.quiz.controllers;

import com.example.quiz.models.Question;
import com.example.quiz.models.QuestionOption;
import com.example.quiz.models.Quiz;
import com.example.quiz.payload.requests.QuizRequest;
import com.example.quiz.services.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/quizzes")
public class QuizController {

    public QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // get all quizzes or get quizzes title containing
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<List<Quiz>> getAllQuizzes(@RequestBody QuizRequest quizRequest) {
        if (quizRequest.getTitleSearch() != null) {
            return ResponseEntity.ok(quizService.searchQuizzes(quizRequest.getTitleSearch()));
        }
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    // create new quiz
    @PostMapping("")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> createQuiz(@RequestBody QuizRequest quizRequest) {
        if (!quizRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Title, description, startsAt, endsAt, and questions are required.");
        }
        return ResponseEntity.ok(quizService.createQuiz(quizRequest.toQuiz()));
    }

    // get quiz by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    // update quiz by id
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> updateQuizById( @PathVariable Long id, @RequestBody QuizRequest quizRequest) {
        if (!quizRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Title, description, startsAt, endsAt, and questions are required.");
        }
        return ResponseEntity.ok(quizService.updateQuizById(id, quizRequest.toQuiz()));
    }

    // delete quiz by id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok("Quiz deleted successfully.");
    }
}
