package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.entity.History;
import com.example.Library.entity.Library;
import com.example.Library.entity.Reader;
import com.example.Library.enums.Status;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.HistoryRepository;
import com.example.Library.repository.LibraryRepository;
import com.example.Library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class LibraryService {
    BookRepository bookRepository;
    BookService bookService;
    ReaderService readerService;
    ReaderRepository readerRepository;
    LibraryRepository libraryRepository;
    HistoryRepository historyRepository;

    public Library getById(Long id) throws DataAccessException {
        try {
            return libraryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Library with id " + id + " not found"));
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to get library with id " + id, e) {
            };
        }
    }

    public Library save(Library library) throws DataAccessException {
        try {
            return libraryRepository.save(library);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to create library", e) {
            };
        }
    }

    public String issueOfTheBook(Long readerId, Long bookId) throws DataAccessException {
        try {
            Book book = bookRepository.findById(bookId).orElseThrow(() ->
                    new IllegalArgumentException("Invalid book ID"));
            Reader reader = readerRepository.findById(readerId).orElseThrow(() ->
                    new IllegalArgumentException("Invalid reader ID"));
            if (book.getStatus() == Status.NOT_AVAILABLE) {
                return "Книга уже занята";
            }
            book.setStatus(Status.NOT_AVAILABLE);
            book.setReader(reader);
            List<Book> bookList = new ArrayList<>();
            bookList.add(book);
            reader.setBooks(bookList);
            reader.setBooksRead(bookList);
            readerService.update(reader);
            History history = new History();
            history.setBooks(book);
            history.setReaders(reader);
            history.setIssueDate(LocalDate.now());
            historyRepository.save(history);
            return "Читатель " + reader.getFullName() + " взял книгу " + book.getNameOfBook() + ".";
        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage()) {
            };
        }
    }

    public String returnOfTheBook(Long readerId, Long bookId) throws DataAccessException {
        try {

            Book book = bookService.getById(bookId);
            Reader reader = readerService.getById(readerId);
            book.setStatus(Status.AVAILABLE);
            History history = reader.getHistory();
            book.setReader(null);
            String bookName = book.getNameOfBook();
            reader.setBooks(null);
            history.setDeliveryDate(LocalDate.now());
            historyRepository.save(history);
            return "Читатель " + reader.getFullName() + " вернул книгу "
                    + bookName;
        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage()) {
            };
        }

    }
}
