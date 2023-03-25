package com.example.Library.entity;

import com.example.Library.enums.AuthStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    private String userName;
    private String password;
    private AuthStatus authStatus;


    @OneToMany
    private List<Book> books;
}
