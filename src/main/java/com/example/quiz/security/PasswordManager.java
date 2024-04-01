package com.example.quiz.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordManager {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static String encode(String password) {
        return passwordEncoder().encode(password);
    }

    public static boolean matches(String password, String encodedPassword) {
        return passwordEncoder().matches(password, encodedPassword);
    }
}
