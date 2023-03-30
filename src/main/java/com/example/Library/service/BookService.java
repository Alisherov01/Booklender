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

    public List<Book> findAll()  throws DataAccessException{
        try {
            return bookRepository.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to get all books", e){};
        }
    }

    public Book save(Book book)  throws DataAccessException{
        try {
            return bookRepository.save(book);
        }catch (DataAccessException e) {
            throw new DataAccessException("Failed to create book", e){};       }
    }

    public Book update(Book book)  throws DataAccessException{
        try {
            return bookRepository.save(book);

        } catch (OptimisticLockException ex) {
            throw new OptimisticLockException("Error occurred while updating book: " + ex.getMessage()) {};
        }
    }

    public String delete(Long id) throws Exception {
        try {
            bookRepository.deleteById(id);
            return "Book delete";
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to delete object with id " + id, e) {
            };
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Object with id " + id + " not found", e);
        } catch (Exception e) {
            throw new Exception("Failed to delete object with id " + id, e);
        }
    }

    public List<Book> findAllByGenre(String genre)  throws DataAccessException{
        try {
            return bookRepository.findAllByGenre(genre);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to get all books by genre", e){};
        }
    }

    public Book findBookByNameOfBook(String name) throws DataAccessException {
        try{
            return bookRepository.findByNameOfBook(name);
        }catch (DataAccessException e) {
            throw new DataAccessException("Failed to get all books by genre", e){};
        }
    }
}
