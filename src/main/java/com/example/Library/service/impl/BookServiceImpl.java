package com.example.Library.service.impl;

import com.example.Library.dto.BookDto;
import com.example.Library.dto.BookSaveDTO;
import com.example.Library.entity.Book;
import com.example.Library.enums.Status;
import com.example.Library.mapper.BookMapper;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id).get();
        if (book.getRemoveDate() == null) {
            return mapper.map(book);
        }
        return null;
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll()
                .stream()
                .filter(book -> book.getRemoveDate() == null)
                .collect(Collectors.toList());
        return mapper.map(books);
    }

    @Override
    public BookDto create(BookDto dto) {
        Book book = bookRepository.save(mapper.map(dto));
        return mapper.map(book);
    }

    @Override
    public BookSaveDTO saveNewBook(BookSaveDTO dto) {
        Book book = new Book();
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        book.setGenre(dto.getGenre());
        book.setStatus(Status.AVAILABLE);
        book.setName(dto.getName());
        bookRepository.save(book);
        return dto;
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
            book.setStatus(dto.getStatus());

            bookRepository.save(book);
        }
        return mapper.map(book);
    }

    @Override
    public String deleteById(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setRemoveDate(LocalDate.now());
        bookRepository.save(book);
        return book.getName();
    }

    @Override
    public List<BookDto> getFreeBooks() {
        List<Book> books = bookRepository.findAllByStatusAvailable();
        return mapper.map(books);
    }

    @Override
    public List<Book> getReadingBooksByUserId(Long id) {
        return bookRepository.getReadingBooksByUserId(id);

    }

    @Override
    public List<String> getAllBooksByUserId(Long id) {
        return bookRepository.getAllBooksByUserId(id);
    }
}
