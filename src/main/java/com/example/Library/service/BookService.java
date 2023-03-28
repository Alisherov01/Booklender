package com.example.Library.service;

import com.example.Library.dto.BookDTO;
import com.example.Library.dto.BookInfoDTO;
import com.example.Library.entity.Book;
import com.example.Library.entity.Reader;
import com.example.Library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    BookRepository bookRepository;

    public Book getById(Long id) throws DataAccessException {
        try {
            return bookRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to get book with id " + id, e){};
        }
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        try {
            return bookRepository.save(book);

        } catch (OptimisticLockException ex) {
            throw new OptimisticLockException("Error occurred while updating book: " + ex.getMessage()) {};
        }
    }

    public String delete(Long id) {
        bookRepository.deleteById(id);
        return "Delete";

    }

    public List<Book> findAllByGenre(String genre) {
        return bookRepository.findAllByGenre(genre);
    }

    public Book findBookByNameOfBook(String name) {
        return bookRepository.findByNameOfBook(name);
    }
}
