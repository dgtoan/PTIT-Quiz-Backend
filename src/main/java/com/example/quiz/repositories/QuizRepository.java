package com.example.quiz.repositories;

import com.example.quiz.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByTitleContaining(String title);

    List<Quiz> findQuizzesByQuestionsId(Long questionId);
}
