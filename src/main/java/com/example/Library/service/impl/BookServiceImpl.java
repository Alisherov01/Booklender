package com.example.Library.service.impl;

import com.example.Library.dto.BookDTO;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

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

    @Override
    public BookDTO getById(Long id) {
        Book book = bookRepository.findById(id).get();
        return toDto(book);
    }

    @Override
    public Book getByIdEntity(Long id) {
        Book book = bookRepository.findById(id).get();
        return book;
    }

    @Override
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
    @Override
    public BookDTO create(Book book) {
        return toDto(bookRepository.save(book));
    }

    //??
    @Override
    public BookDTO update(Book book) {
        return toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setRemoveDate(LocalDate.now());
        bookRepository.save(book);
    }

    @Override
    public List<BookDTO> getFreeBooks() {
        List<Book> books = bookRepository.getFreeBooks();
        List<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : books) {
            BookDTO dto = toDto(book);
            bookDTOs.add(dto);
        }
        return bookDTOs;
    }

    @Override
    public List<String> getReadingBooksByUserId(Long id) {
        return bookRepository.getReadingBooksByUserId(id);

    }

    @Override
    public List<String> getAllBooksByUserId(Long id) {
        return bookRepository.getAllBooksByUserId(id);
    }
}
