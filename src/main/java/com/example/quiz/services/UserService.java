package com.example.quiz.services;

import com.example.quiz.models.User;
import com.example.quiz.payload.requests.UserRequest;
import com.example.quiz.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // search users by username containing
    public List<User> searchUsers(String name) {
        return userRepository.findUsersByUsernameContainingIgnoreCase(name);
    }

    // get user by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // create user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // update user
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(user.getRoles());
        return userRepository.save(existingUser);
    }

    // delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}