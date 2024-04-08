package com.example.quiz.controllers;

import com.example.quiz.exeption.ResourceNotFoundException;
import com.example.quiz.models.Quiz;
import com.example.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    QuizRepository quizRepository;

    // get all quizzes or get quizzes by containing title
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<Quiz>> getAllQuizzes(@RequestBody Quiz quiz) {
        List<Quiz> quizzes = new ArrayList<Quiz>();
        if (quiz.getTitle() != null) {
            quizRepository.findByTitleContaining(quiz.getTitle()).forEach(quizzes::add);
            return ResponseEntity.ok(quizzes);
        }

        quizRepository.findAll().forEach(quizzes::add);
        return ResponseEntity.ok(quizzes);
    }

    // get quiz by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));

        return ResponseEntity.ok(quiz);
    }

    // create new quiz
    @PostMapping("")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz _quiz = quizRepository.save(quiz);
        return ResponseEntity.ok(_quiz);
    }

    // update quiz by id
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        Quiz _quiz = quizRepository.findById(id).map(q -> {
            q.setTitle(quiz.getTitle());
            q.setDescription(quiz.getDescription());
            q.setStartsAt(quiz.getStartsAt());
            q.setEndsAt(quiz.getEndsAt());
            return quizRepository.save(q);
        }).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));

        return ResponseEntity.ok(_quiz);
    }

    // delete quiz by id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long id) {
        quizRepository.deleteById(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }
}
