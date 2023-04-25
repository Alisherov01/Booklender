package com.example.Library.dto;

import com.example.Library.entity.Borrowing;
import com.example.Library.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private List<Borrowing> borrowings;
}





