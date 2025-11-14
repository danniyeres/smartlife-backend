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
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;


}
