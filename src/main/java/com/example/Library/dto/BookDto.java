package com.example.Library.dto;

import com.example.Library.entity.Borrowing;
import lombok.*;

import java.util.List;

@Data
public class BookDto {
    //private Long id;
    private String name;
    private String description;
    private String author;
    private String vendorCode;
    private String genre;
    private List<Borrowing> borrowings;
}





