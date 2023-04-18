package com.example.Library.entity;

import com.example.Library.enums.AuthStatus;
import com.example.Library.enums.Roles;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String login;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private AuthStatus authStatus;

    @Enumerated(value = EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "user")
    private List<Borrowing> borrowings;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate removeDate;

}
