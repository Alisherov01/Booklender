package com.example.Library.service;

import com.example.Library.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    BookDto getById(Long id);
    List<BookDto> getAll();
    BookDto create(BookDto dto);
    BookDto update(Long id, BookDto dto);
    void deleteById(Long id);
    List<BookDto> getFreeBooks();
    List<String> getReadingBooksByUserId(Long id);
    List<String> getAllBooksByUserId(Long id);
}
