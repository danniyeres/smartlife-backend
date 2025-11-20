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

    public void addUser(User user) {
        log.info("Adding new user: {}", user.getUsername());
        userRepository.save(User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .build());
    }

    public UserDto updateUser(Long userId, UserUpdateDto userDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (userDto.getFirstName() != null) {
            log.info("Updating first name for user {}: {} -> {}", existingUser.getUsername(), existingUser.getFirstName(), userDto.getFirstName());
            existingUser.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            log.info("Updating last name for user {}: {} -> {}", existingUser.getUsername(), existingUser.getLastName(), userDto.getLastName());
            existingUser.setLastName(userDto.getLastName());
        }
        if (userDto.getUsername() != null) {
            log.info("Updating username for user {}: {} -> {}", existingUser.getUsername(), existingUser.getUsername(), userDto.getUsername());
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null) {
            log.info("Updating email for user {}: {} -> {}", existingUser.getUsername(), existingUser.getEmail(), userDto.getEmail());
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getAvatar() != null) {
            log.info("Updating avatar for user {}", existingUser.getUsername());
            existingUser.setAvatar(userDto.getAvatar());
        }

        User updatedUser = userRepository.save(existingUser);

        log.info("User updated: {}", updatedUser.getUsername());
        return UserDto.builder()
                .id(updatedUser.getId())
                .firstName(updatedUser.getFirstName())
                .lastName(updatedUser.getLastName())
                .username(updatedUser.getUsername())
                .email(updatedUser.getEmail())
                .avatar(updatedUser.getAvatar())
                .build();
    }

    public UserDto changePassword(Long userId, String newPassword) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));


        existingUser.setPassword(passwordEncoder.encode(newPassword));
        User updatedUser = userRepository.save(existingUser);

        log.info("User password changed: {}", updatedUser.getUsername());
        return UserDto.builder()
                .id(updatedUser.getId())
                .username(updatedUser.getUsername())
                .firstName(updatedUser.getFirstName())
                .lastName(updatedUser.getLastName())
                .email(updatedUser.getEmail())
                .avatar(updatedUser.getAvatar())
                .build();
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return UserDto.builder()

                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .build();
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
