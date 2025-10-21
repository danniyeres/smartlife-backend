package org.example.smartlifebackend.dto;

import lombok.*;
import org.example.smartlifebackend.model.User;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
    private String provider;


    public User toEntity() {
        return User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .password(password)
                .provider(provider)
                .build();
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .provider(user.getProvider())
                .build();
    }

}
