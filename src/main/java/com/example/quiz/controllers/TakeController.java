package com.example.quiz.controllers;

import com.example.quiz.payload.requests.TakeRequest;
import com.example.quiz.services.QuizService;
import com.example.quiz.services.TakeService;
import com.example.quiz.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/takes")
public class TakeController {
    public TakeService takeService;
    public QuizService quizService;
    public UserService userService;

    public TakeController(TakeService takeService, QuizService quizService, UserService userService) {
        this.takeService = takeService;
        this.quizService = quizService;
        this.userService = userService;
    }

    // get all takes
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<?> getAllTakes() {
        return ResponseEntity.ok(takeService.getAllTakes());
    }

    // create new take
    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<?> createTake(@RequestBody TakeRequest takeRequest) {
        if (!takeRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Score, quiz, and user are required.");
        }
        return ResponseEntity.ok(takeService.createTake(takeRequest.toTake(quizService, userService)));
    }

    // get take by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    ResponseEntity<?> getTakeById(@PathVariable Long id) {
        return ResponseEntity.ok(takeService.getTakeById(id));
    }

    // update take by id
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> updateTakeById(@PathVariable Long id, @RequestBody TakeRequest takeRequest) {
        if (!takeRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Score, quiz, and user are required.");
        }
        return ResponseEntity.ok(takeService.updateTake(id, takeRequest.toTake(quizService, userService)));
    }

    // delete take by id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    ResponseEntity<?> deleteTakeById(@PathVariable Long id) {
        takeService.deleteTake(id);
        return ResponseEntity.ok("Take deleted successfully.");
    }

}
