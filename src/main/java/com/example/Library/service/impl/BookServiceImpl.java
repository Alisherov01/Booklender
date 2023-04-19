package com.example.Library.service.impl;

import com.example.Library.dto.BookDTO;
import com.example.Library.dto.BookSaveDTO;
import com.example.Library.entity.Book;
import com.example.Library.enums.Status;
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
                book.getStatus(),
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

    //??
    @Override
    public BookDTO update(Long id, BookDTO dto) {
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
        return toDto(book);
    }

    @Override
    public String deleteById(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setRemoveDate(LocalDate.now());
        bookRepository.save(book);
        return book.getName();
    }
//
//    @Override
//    public List<BookDTO> getFreeBooks() {
//        List<Book> books = bookRepository.getFreeBooks();
//        List<BookDTO> bookDTOs = new ArrayList<>();
//
//        for (Book book : books) {
//            if(book.getRemoveDate() == null){
//                BookDTO dto = toDto(book);
//                bookDTOs.add(dto);
//            }
//
//        }
//        return bookDTOs;
//    }


//    @Override
//    public List<BookDTO> getFreeBooks() {
//        List<Book> books = bookRepository.getFreeBooks();
//        List<BookDTO> bookDTOs = new ArrayList<>();
//
//        for (Book book : books) {
//            BookDTO dto = toDto(book);
//            bookDTOs.add(dto);
//        }
//        return bookDTOs;
//    }


    @Override
    public List<Book> getFreeBooks() {
        List<Book> books = bookRepository.findAllByStatusAvailable();
        return books;
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
