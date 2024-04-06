package com.example.quiz.repositories;

import com.example.quiz.models.Question;
import com.example.quiz.models.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}