package org.example.smartlifebackend.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthResponse {
    private UserResponse user;
    private String accessToken;
    private String refreshToken;
}
