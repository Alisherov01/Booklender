package com.example.Library.dto;

import com.example.Library.entity.Borrowing;
import com.example.Library.enums.AuthStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String userName;
    private String password;
    private AuthStatus authStatus;
    private List<Borrowing> borrowings;
}
