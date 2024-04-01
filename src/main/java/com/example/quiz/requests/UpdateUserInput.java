package com.example.quiz.requests;

import com.example.quiz.entities.User;
import com.example.quiz.security.PasswordManager;

public record UpdateUserInput(
        String classId,
        String fullName,
        String email,
        String password,
        String username,
        String role
) {
    public void update(User user) {
        user.setClassId(classId);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPasswordHash(PasswordManager.encode(password));
        user.setRole(role);
    }
}
