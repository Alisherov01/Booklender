package com.example.Library.entity;

import com.example.Library.enums.AuthStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String userName;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private AuthStatus authStatus;

    @OneToMany(mappedBy = "user")
    private List<Borrowing> borrowings;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate removeDate;

}
