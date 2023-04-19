package com.example.Library.service;

import com.example.Library.dto.BookDTO;
import com.example.Library.dto.BookSaveDTO;
import com.example.Library.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    BookDTO getById(Long id);
    Book getByIdEntity(Long id);
    List<BookDTO> getAll();
    BookDTO create(Book book);
    BookDTO update(Long id , BookDTO dto);
    String deleteById(Long id);
    List<Book> getFreeBooks();
    List<Book> getReadingBooksByUserId(Long id);
    List<String> getAllBooksByUserId(Long id);
    BookSaveDTO saveNewBook(BookSaveDTO dto);
}
