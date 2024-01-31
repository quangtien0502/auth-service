package com.example.ra.model.dto.Request.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserLogin {
    @NotNull(message = "User name not null")
    @NotEmpty(message = "user name not empty")
    @NotBlank(message = "User not blank")
    private String userName;
    @NotNull(message = "password not null")
    @NotEmpty(message = "password not empty")
    @NotBlank(message = "password blank")
    private String password;
}
