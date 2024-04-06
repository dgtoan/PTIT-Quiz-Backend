package com.example.quiz.repositories;

import com.example.quiz.models.TakeAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeAnswerRepository extends JpaRepository<TakeAnswer, Long> {
}