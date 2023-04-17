package com.example.Library.service.impl;

import com.example.Library.dto.BookDto;
import com.example.Library.entity.Book;
import com.example.Library.mapper.BookMapper;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    @Override
    public BookDto getById(Long id) {
        return mapper.map(bookRepository.findById(id).get());
    }

    @Override
    public List<BookDto> getAll() {
        return mapper.map(bookRepository.findAll());
    }

    @Override
    public BookDto create(BookDto dto) {
        Book book = bookRepository.save(mapper.map(dto));
        return mapper.map(book);
    }

    @Override
    public BookDto update(Long id, BookDto dto) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setName(dto.getName());
            book.setDescription(dto.getDescription());
            book.setGenre(dto.getGenre());
            book.setVendorCode(dto.getVendorCode());
            book.setAuthor(dto.getAuthor());
            book.setBorrowings(dto.getBorrowings());

            bookRepository.save(book);
        }
        return mapper.map(book);
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setRemoveDate(LocalDate.now());
        bookRepository.save(book);
    }

    @Override
    public List<BookDto> getFreeBooks() {
        List<Book> books = bookRepository.getFreeBooks();
        return mapper.map(books);
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
