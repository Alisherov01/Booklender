package com.example.Library.dto;

import com.example.Library.entity.Reader;
import com.example.Library.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String author;
    private String nameOfBook;
    private Status status;
    private Reader reader;
}
