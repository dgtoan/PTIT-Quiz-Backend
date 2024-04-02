package com.example.quiz.repositories;

import com.example.quiz.models.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeRepository extends JpaRepository<Take, Long> {
}