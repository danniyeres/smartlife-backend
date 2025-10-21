package org.example.smartlifebackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartlifebackend.dto.request.LoginDto;
import org.example.smartlifebackend.dto.request.RegisterDto;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.security.AuthService;
import org.example.smartlifebackend.security.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String token = authService.register(registerDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> me(@CurrentUser User user) {
        Map<String, String> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("firstName", user.getFirstName());
        return ResponseEntity.ok(response);
    }

}
