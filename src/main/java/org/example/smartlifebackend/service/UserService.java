package org.example.smartlifebackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartlifebackend.dto.UserDto;
import org.example.smartlifebackend.dto.request.UserUpdateDto;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(UserDto userDto) {
        log.info("Adding new user: {}", userDto.getUsername());
        userRepository.save(userDto.toEntity());
    }

    public UserDto updateUser(Long userId, UserUpdateDto userDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (userDto.getFirstName() != null) existingUser.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null) existingUser.setLastName(userDto.getLastName());
        if (userDto.getUsername() != null) existingUser.setUsername(userDto.getUsername());
        if (userDto.getEmail() != null) existingUser.setEmail(userDto.getEmail());
        if (userDto.getAvatarUrl() != null) existingUser.setAvatarUrl(userDto.getAvatarUrl());

        User updatedUser = userRepository.save(existingUser);

        log.info("User updated: {}", updatedUser.getUsername());
        return UserDto.fromEntity(updatedUser);
    }

    public UserDto changePassword(Long userId, String newPassword) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));


        existingUser.setPassword(passwordEncoder.encode(newPassword));
        User updatedUser = userRepository.save(existingUser);

        log.info("User password changed: {}", updatedUser.getUsername());
        return UserDto.fromEntity(updatedUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
