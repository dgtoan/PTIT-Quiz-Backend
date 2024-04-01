package com.example.quiz.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user")
@Entity
@Getter
@Setter
@ToString
public class User {
/*
CREATE TABLE `quiz`.`user` (
 `id` INT AUTO_INCREMENT,
 `classId` VARCHAR(20),
 `fullName` VARCHAR(100),
 `email` VARCHAR(50),
 `username` VARCHAR(50),
 `passwordHash` VARCHAR(32),
 `role` VARCHAR(20)
);
 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "classId", nullable = false, length = 20)
    private String classId;

    @Column(name = "fullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "passwordHash", nullable = false, length = 100)
    private String passwordHash;

    @Column(name = "role", nullable = false, length = 20)
    private String role;
}
