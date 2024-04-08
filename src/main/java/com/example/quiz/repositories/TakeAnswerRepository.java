package com.example.quiz.repositories;

import com.example.quiz.models.TakeAnswer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeAnswerRepository extends JpaRepository<TakeAnswer, Long> {
    List<TakeAnswer> findByTakeId(Long takeId);

    List<TakeAnswer> findByQuestionId(Long questionId);

    @Transactional
    void deleteByTakeId(Long takeId);

    @Transactional
    void deleteByQuestionId(Long questionId);
}