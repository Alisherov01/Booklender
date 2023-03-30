package com.example.Library.entity;

import com.example.Library.enums.AuthStatus;
import com.example.Library.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Enumerated(value = EnumType.STRING)
    private Roles role;
    @OneToMany
    private List<Book> booksRead;
}
