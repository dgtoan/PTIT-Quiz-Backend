package com.example.quiz.controllers;

import com.example.quiz.entities.User;
import com.example.quiz.requests.CreateUserInput;
import com.example.quiz.requests.UpdateUserInput;
import com.example.quiz.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 `id` INT AUTO_INCREMENT,
 `classId` INT,
 `fullName` VARCHAR(100),
 `email` VARCHAR(50),
 `passwordHash` VARCHAR(32),
 `role` VARCHAR(20),
 */
@RestController
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody CreateUserInput createUserInput) {
        return ResponseEntity.ok(userService.createUser(createUserInput.toUser()));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UpdateUserInput updateUserInput) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        updateUserInput.update(user);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}