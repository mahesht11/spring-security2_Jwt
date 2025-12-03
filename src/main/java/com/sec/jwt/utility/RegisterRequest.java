package com.sec.jwt.utility;

import com.sec.jwt.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "full name is required")
    @Size(min=3, max = 12, message="fullname length should be 3 to 12")
    private String fullName;

    @NotBlank(message = "user name is required")
    @Size(min=3, max = 12, message="username length should be 3 to 8")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min=3, max = 12, message="password length should be 3 to 12")
    private String password;

    private Role role;
}
