package com.example.quiz.repositories;

import com.example.quiz.entities.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeRepository extends JpaRepository<Take, Long> {
}