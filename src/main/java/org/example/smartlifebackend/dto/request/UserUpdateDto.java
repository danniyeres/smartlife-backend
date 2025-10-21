package org.example.smartlifebackend.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String avatarUrl;
}
