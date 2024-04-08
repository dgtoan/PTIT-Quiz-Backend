package com.example.quiz.controllers;

import com.example.quiz.exeption.ResourceNotFoundException;
import com.example.quiz.models.Question;
import com.example.quiz.models.Quiz;
import com.example.quiz.repositories.QuestionOptionRepository;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class QuestionController {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionOptionRepository questionOptionRepository;

    // get all questions or get questions containing
    @GetMapping("/questions")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<Question>> getQuestions(@RequestBody Question question) {
        List<Question> questions;

        if (question.getQuestion() != null) {
            questions = questionRepository.findByQuestionContaining(question.getQuestion());
        } else {
            questions = questionRepository.findAll();
        }

        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(questions);
    }

    // get question by id
    @GetMapping("/questions/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionRepository.findById(id).orElse(null);

        if (question == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(question);
    }

    // get all questions by quiz id
    @GetMapping("/quizzes/{id}/questions")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<Question>> getQuestionsByQuizId(@PathVariable Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quiz not found with id: " + id);
        }
        // print on debug
        System.out.println("Quiz id: " + id);

        List<Question> questions = questionRepository.findQuestionsByQuizzesId(id);

        return ResponseEntity.ok(questions);
    }

    // get all quiz has question by question id
    @GetMapping("/questions/{id}/quizzes")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<Quiz>> getQuizzesByQuestionId(@PathVariable Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }

        List<Quiz> quizzes = quizRepository.findQuizzesByQuestionsId(id);

        return ResponseEntity.ok(quizzes);
    }

    // create new question
    @PostMapping("/questions")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question _question = new Question();
        _question.setQuestion(question.getQuestion());
        _question.setPoints(question.getPoints());

        questionRepository.save(_question);

        return ResponseEntity.ok(_question);
    }

    // create new question by quiz id
    @PostMapping("/quizzes/{id}/questions")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Question> createQuestionByQuizId(@PathVariable Long id, @RequestBody Question question) {
        Question _question = quizRepository.findById(id).map(quiz -> {
            Long questionId = question.getId();
            if (questionId != null) {
                Question _question1 = questionRepository.findById(questionId)
                        .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + questionId));
                quiz.addQuestion(_question1);
                return questionRepository.save(_question1);
            }

            // add and create new question
            quiz.addQuestion(question);
            return questionRepository.save(question);
        }).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));

        return ResponseEntity.ok(_question);
    }

    // update question by id
    @PutMapping("/questions/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Question> updateQuestionById(@PathVariable Long id, @RequestBody Question question) {
        Question _question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));

        _question.setQuestion(question.getQuestion());
        _question.setPoints(question.getPoints());

        return ResponseEntity.ok(questionRepository.save(_question));
    }

    // delete question by id
    @DeleteMapping("/questions/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));

        questionRepository.delete(question);

        return ResponseEntity.ok("Question deleted successfully.");
    }
}
