package com.example.quiz.repositories;

import com.example.quiz.models.Take;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeRepository extends JpaRepository<Take, Long> {
    List<Take> findTakesByUserId(Long userId);
    List<Take> findTakesByQuizId(Long quizId);

    @Transactional
    void deleteByUserId(Long userId);

    @Transactional
    void deleteByQuizId(Long quizId);
}