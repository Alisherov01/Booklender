package com.example.Library.dto;

import com.example.Library.entity.Borrowing;
import com.example.Library.enums.AuthStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
public class UserDto {
    //private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String fullName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid cannot be blank")
    private String email;

    @NotBlank(message = "User name cannot be blank")
    @Size(max = 20)
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    private String password;

    private AuthStatus authStatus;
    private List<Borrowing> borrowings;
}
