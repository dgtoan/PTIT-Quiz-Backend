package com.example.quiz.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Table(name = "quiz")
@Entity
@Getter
@Setter
@ToString
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "classId", nullable = false, length = 20)
    private String classId;

    @Column(name = "title", nullable = false, length = 75)
    private String title;

    @Column(name = "metaTitle", nullable = false, length = 100)
    private String metaTitle;

    @Column(name = "startsAt", nullable = false)
    private Timestamp startsAt;

    @Column(name = "endsAt", nullable = false)
    private Timestamp endsAt;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
}
