package com.example.Library.entity;

import com.example.Library.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String shortDescription;
    private String nameOfBook;
    private String vendorCode;
    private Status status;
    private LocalDate issueDate;
    private LocalDate deliveryDate;

    @ManyToOne
    private Library library;

    @ManyToOne
    private Reader reader;
}
