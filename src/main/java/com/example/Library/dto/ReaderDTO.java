package com.example.Library.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReaderDTO {
    private String name;
    private String email;
    private String login;
    private String password;
}
