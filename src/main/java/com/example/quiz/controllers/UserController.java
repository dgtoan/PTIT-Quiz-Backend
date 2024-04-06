package com.example.quiz.controllers;

import com.example.quiz.models.User;
import com.example.quiz.payload.requests.UserRequest;
import com.example.quiz.repositories.RoleRepository;
import com.example.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    public UserService userService;

    @Autowired
    public RoleRepository roleRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get all users or get users by containing username
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> getAllUsers(@RequestBody UserRequest userRequest) {
        if (userRequest.getNameSearch() != null) {
            return ResponseEntity.ok(userService.searchUsers(userRequest.getUsername()));
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // create new user
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        if (!userRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Username, email, password and roles are required.");
        }

        return ResponseEntity.ok(userService.createUser(userRequest.toUser(roleRepository)));
    }

    // get user by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // update user by id
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        if (!userRequest.checkInput()) {
            return ResponseEntity.badRequest().body("Username, email, password and roles are required.");
        }

        User user = userService.updateUser(id, userRequest.toUser(roleRepository));
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // delete user by id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
