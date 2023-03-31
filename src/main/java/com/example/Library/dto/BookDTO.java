package com.example.Library.dto;

import com.example.Library.entity.Borrowing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private String description;
    private String author;
    private String vendorCode;
    private String genre;
    private List<Borrowing> borrowings;
}





