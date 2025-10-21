package org.example.smartlifebackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartlifebackend.dto.UserDto;
import org.example.smartlifebackend.dto.request.UserUpdateDto;
import org.example.smartlifebackend.model.User;
import org.example.smartlifebackend.security.CurrentUser;
import org.example.smartlifebackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@CurrentUser User user, UserUpdateDto userDto) {
        UserDto updatedUser = userService.updateUser(user.getId(), userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/change-password")
    public ResponseEntity<UserDto> changePassword(@CurrentUser User user, String newPassword) {
        UserDto updatedUser = userService.changePassword(user.getId(), newPassword);
        return ResponseEntity.ok(updatedUser);
    }
}
