package org.example.smartlifebackend.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartlifebackend.dto.request.LoginDto;
import org.example.smartlifebackend.dto.request.RegisterDto;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterDto registerDto) {
        if (userService.existsByUsername(registerDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userService.existsByEmail(registerDto.getEmail())) {
            throw new IllegalArgumentException("Email is already taken");
        }

        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userService.addUser(registerDto.toUserDto());
        log.info("User registered: {}", registerDto.getUsername());
        return jwtService.generateToken(registerDto.getUsername());
    }

    public String login(LoginDto loginDto) {
        User existingUser = userService.findByUsername(loginDto.getUsername());
        if (existingUser == null || !passwordEncoder.matches(loginDto.getPassword(), existingUser.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        log.info("User logged in: {}", loginDto.getUsername());
        return jwtService.generateToken(existingUser.getUsername());
    }
}
