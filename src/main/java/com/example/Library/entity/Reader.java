package com.example.Library.entity;

import com.example.Library.enums.AuthStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    private String login;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private AuthStatus authStatus;
    @ManyToOne
    private History history;
    @OneToMany
    private List<Book> books;
    @OneToMany
    private List<Book> booksRead;
}
