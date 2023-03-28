package com.example.Library.entity;

import com.example.Library.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String shortDescription;
    private String nameOfBook;
    private String vendorCode;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String genre;
    @ManyToOne
    private Library library;
    @ManyToOne
    private History history;
    @ManyToOne
    private Reader reader;
}
