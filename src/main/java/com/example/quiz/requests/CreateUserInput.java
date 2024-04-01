package com.example.quiz.requests;

import com.example.quiz.entities.User;
import com.example.quiz.security.PasswordManager;

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
public record CreateUserInput (
        String classId,
        String fullName,
        String email,
        String password,
        String username,
        String role
) {
    public User toUser() {
        User user = new User();
        user.setClassId(classId);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPasswordHash(PasswordManager.encode(password));
        user.setRole(role);
        return user;
    }
}
