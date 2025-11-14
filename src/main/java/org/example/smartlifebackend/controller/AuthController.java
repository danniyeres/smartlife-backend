package org.example.smartlifebackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartlifebackend.dto.UserDto;
import org.example.smartlifebackend.dto.request.LoginDto;
import org.example.smartlifebackend.dto.request.RegisterDto;
import org.example.smartlifebackend.dto.request.UserUpdateDto;
import org.example.smartlifebackend.dto.response.AuthResponse;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.security.AuthService;
import org.example.smartlifebackend.security.CurrentUser;
import org.example.smartlifebackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDto registerDto) {
        AuthResponse response = authService.register(registerDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        AuthResponse response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(@CurrentUser User user) {
        UserDto userDto = userService.getUserById(user.getId());
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/me")
    public ResponseEntity<UserDto> update(@CurrentUser User user, @RequestBody UserUpdateDto userUpdateDto) {
        UserDto updatedUser = userService.updateUser(user.getId(), userUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }
}
