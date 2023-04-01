package com.example.Library.service;

import com.example.Library.dto.BookDTO;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private BookDTO toDto(Book book) {
        return new BookDTO(
                book.getId(),
                book.getName(),
                book.getDescription(),
                book.getAuthor(),
                book.getVendorCode(),
                book.getGenre(),
                book.getBorrowings());
    }

    public BookDTO getById(Long id) {
        Book book = bookRepository.findById(id).get();
        return toDto(book);
    }

    public Book getByIdEntity(Long id) {
        Book book = bookRepository.findById(id).get();
        return book;
    }

    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : books) {
            if(book.getRemoveDate() == null) {
            BookDTO dto = toDto(book);
            bookDTOs.add(dto);
        }}
        return bookDTOs;
    }

    //??
    public BookDTO create(Book book) {
        return toDto(bookRepository.save(book));
    }

    //??
    public BookDTO update(Book book) {
        return toDto(bookRepository.save(book));
    }

    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setRemoveDate(LocalDate.now());
        bookRepository.save(book);
    }

    public List<BookDTO> getFreeBooks() {
        List<Book> books = bookRepository.getFreeBooks();
        List<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : books) {
            BookDTO dto = toDto(book);
            bookDTOs.add(dto);
        }
        return bookDTOs;
    }

    public List<String> getReadingBooksByUserId(Long id) {
        return bookRepository.getReadingBooksByUserId(id);

    }

    public List<String> getBooksByUserId(Long id) {
        return bookRepository.getAllBooksByUserId(id);
    }
}
