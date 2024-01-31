package com.example.ra.model.dto.Request.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRegister {
    @NotEmpty(message = "not Empty")
    private String fullName;
    @NotEmpty(message = "not empty")
    private String userName;
    @NotEmpty(message = "not empty")
    @Length(min=6,max=30,message = "Password must between 6 and 30")
    private String password;
}
