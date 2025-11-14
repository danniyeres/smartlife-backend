package org.example.smartlifebackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.smartlifebackend.dto.UserDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegisterDto {

    private String firstName;
    private String lastName;
    @NotNull (message = "Username cannot be null")
    private String username;
    @NotNull (message = "Email cannot be null")
    private String email;
    @NotNull (message = "Password cannot be null")
    private String password;

}
