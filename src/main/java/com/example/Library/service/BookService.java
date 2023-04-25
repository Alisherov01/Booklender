package com.example.Library.service;

import com.example.Library.dto.BookDto;
import com.example.Library.dto.BookSaveDTO;
import com.example.Library.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    BookDto getById(Long id);
    List<BookDto> getAll();
    BookDto create(BookDto dto);
    BookDto update(Long id , BookDto dto);
    String deleteById(Long id);
    List<BookDto> getFreeBooks();
    List<Book> getReadingBooksByUserId(Long id);
    List<String> getAllBooksByUserId(Long id);
    BookSaveDTO saveNewBook(BookSaveDTO dto);
}
