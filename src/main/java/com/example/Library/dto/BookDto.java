package com.example.Library.dto;

import com.example.Library.entity.Borrowing;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
public class BookDto {
    //private Long id;
    @NotBlank(message = "Name of book cannot be blank")
    @Size(max = 80)
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 600)
    private String description;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 80)
    private String author;

    @NotBlank(message = "Vendor code cannot be blank")
    @Size(max = 8)
    private String vendorCode;

    @NotBlank(message = "Genre cannot be blank")
    @Size(max = 80)
    private String genre;

    private List<Borrowing> borrowings;
}





