package com.example.Library.service;

import com.example.Library.dto.BookDTO;
import com.example.Library.dto.BookInfoDTO;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        Book book2 = new Book();
        book2.setId(book.get().getId());
        book2.setName(book.get().getName());
        book2.setAuthor(book.get().getAuthor());
        /*book2.setLibrary(book.get().getLibrary());
        book2.setReader(book.get().getReader());*/
        book2.setGenre(book.get().getGenre());
        book2.setStatus(book.get().getStatus());
        book2.setDeliveryDate(book.get().getDeliveryDate());
        book2.setIssueDate(book.get().getIssueDate());
        book2.setDescription(book.get().getDescription());
        book2.setVendorCode(book.get().getVendorCode());
        return book2;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<BookDTO> findAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book:bookList) {
            BookDTO bookDTO = new BookDTO(book.getId(),book.getAuthor(),book.getName(),
                    book.getStatus())/*,book.getReader())*/;
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        Book book1 = bookRepository.findById(book.getId()).get();
        if (book1.getName() == null) book1.setName(book.getName());
        if (book1.getAuthor() == null) book1.setAuthor(book.getAuthor());
       /* if (book1.getLibrary() == null) book1.setLibrary(book.getLibrary());
        if (book1.getReader() == null) book1.setReader(book.getReader());*/
        if (book1.getGenre() == null) book1.setGenre(book.getGenre());
        if (book1.getStatus() == null) book1.setStatus(book.getStatus());
        if (book1.getDeliveryDate() == null) book1.setDeliveryDate(book.getDeliveryDate());
        if (book1.getIssueDate() == null) book1.setIssueDate(book.getIssueDate());
        if (book1.getDescription() == null) book1.setDescription(book.getDescription());
        if (book1.getVendorCode() == null) book1.setVendorCode(book.getVendorCode());
        return bookRepository.save(book1);
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
