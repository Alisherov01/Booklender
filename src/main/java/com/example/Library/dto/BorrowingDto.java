package com.example.Library.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class BorrowingDto {
    //private Long id;
    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;

    @NotNull
    @PastOrPresent
    private LocalDate takeData;

    @NotNull
    @PastOrPresent
    private LocalDate returnDate;
}
