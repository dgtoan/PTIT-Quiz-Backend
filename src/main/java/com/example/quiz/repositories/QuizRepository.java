package com.example.quiz.repositories;

import com.example.quiz.entities.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
}