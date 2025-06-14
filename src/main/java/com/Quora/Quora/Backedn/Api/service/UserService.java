package com.Quora.Quora.Backedn.Api.service;

import com.Quora.Quora.Backedn.Api.exceptions.ResourceNotFoundException;
import com.Quora.Quora.Backedn.Api.model.User;
import com.Quora.Quora.Backedn.Api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(UUID userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            // Check for duplicate email (if changed)
            if (updatedUser.getEmail() != null &&
                    !updatedUser.getEmail().equals(user.getEmail()) &&
                    userRepository.existsByEmail(updatedUser.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            // Check for duplicate username (if changed)
            if (updatedUser.getUsername() != null &&
                    !updatedUser.getUsername().equals(user.getUsername()) &&
                    userRepository.existsByUsername(updatedUser.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            if (updatedUser.getUsername() != null) user.setUsername(updatedUser.getUsername());
            if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
            if (updatedUser.getBio() != null) user.setBio(updatedUser.getBio());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
