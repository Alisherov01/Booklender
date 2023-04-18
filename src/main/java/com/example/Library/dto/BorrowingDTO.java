package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
