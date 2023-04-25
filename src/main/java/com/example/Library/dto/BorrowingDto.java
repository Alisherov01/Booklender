package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
