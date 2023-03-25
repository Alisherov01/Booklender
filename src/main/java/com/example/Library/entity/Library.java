package com.example.Library.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany
    private List<Book> books;

}
