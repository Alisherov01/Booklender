package com.example.Library.dto;

import com.example.Library.entity.Book;
import com.example.Library.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDTO {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String user;
    private String book;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate takeData;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
}
