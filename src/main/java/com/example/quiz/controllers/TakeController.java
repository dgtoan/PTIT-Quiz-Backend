package com.example.quiz.controllers;

import com.example.quiz.exeption.ResourceNotFoundException;
import com.example.quiz.models.Take;
import com.example.quiz.repositories.QuizRepository;
import com.example.quiz.repositories.TakeRepository;
import com.example.quiz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class TakeController {
    @Autowired
    TakeRepository takeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuizRepository quizRepository;

    // get all takes by user id
    @GetMapping("/users/{id}/takes")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<Take>> getTakesByUserId(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        List<Take> takes = takeRepository.findTakesByUserId(id);

        return ResponseEntity.ok(takes);
    }

    // get all takes by quiz id
    @GetMapping("/quizzes/{id}/takes")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<Take>> getTakesByQuizId(@PathVariable Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quiz not found with id: " + id);
        }

        List<Take> takes = takeRepository.findTakesByQuizId(id);

        return ResponseEntity.ok(takes);
    }

    // get take by id
    @GetMapping("/takes/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<Take> getTakeById(@PathVariable Long id) {
        Take take = takeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Take not found with id: " + id));

        return ResponseEntity.ok(take);
    }

    // create new take by user id and quiz id
    @PostMapping("/users/{userId}/quizzes/{quizId}/takes")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<Take> createTakeByUserIdAndQuizId(@PathVariable Long userId, @PathVariable Long quizId, @RequestBody Take take) {
        Take _take = userRepository.findById(userId).map(user -> {
            take.setUser(user);
            return quizRepository.findById(quizId).map(quiz -> {
                take.setQuiz(quiz);
                return takeRepository.save(take);
            }).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return ResponseEntity.ok(_take);
    }

    // update take by id
    @PutMapping("/takes/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Take> updateTake(@PathVariable Long id, @RequestBody Take take) {
        Take _take = takeRepository.findById(id).map(take1 -> {
            take1.setScore(take.getScore());
            take1.setCreatedAt(take.getCreatedAt());
            return takeRepository.save(take1);
        }).orElseThrow(() -> new ResourceNotFoundException("Take not found with id: " + id));

        return ResponseEntity.ok(_take);
    }

    // delete take by id
    @DeleteMapping("/takes/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> deleteTake(@PathVariable Long id) {
        takeRepository.deleteById(id);
        return ResponseEntity.ok("Take deleted successfully.");
    }

    // delete all takes by user id
    @DeleteMapping("/users/{id}/takes")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> deleteTakesByUserId(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        takeRepository.deleteByUserId(id);
        return ResponseEntity.ok("Takes deleted successfully.");
    }

    // delete all takes by quiz id
    @DeleteMapping("/quizzes/{id}/takes")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> deleteTakesByQuizId(@PathVariable Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quiz not found with id: " + id);
        }

        takeRepository.deleteByQuizId(id);
        return ResponseEntity.ok("Takes deleted successfully.");
    }
}
