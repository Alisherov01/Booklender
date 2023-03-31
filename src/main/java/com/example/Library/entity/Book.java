package com.example.Library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    private String description;
    private String vendorCode;
    private String genre;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Borrowing> borrowings;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate removeDate;


}
