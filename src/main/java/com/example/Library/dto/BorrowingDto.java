package com.example.Library.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class BorrowingDto {
    //private Long id;
    private Long userId;
    private Long bookId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate takeData;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
}
