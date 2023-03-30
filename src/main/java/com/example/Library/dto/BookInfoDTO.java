package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookInfoDTO {
    private Long id;
    private String author;
    private String nameOfBook;
    private String shortDescription;
}
