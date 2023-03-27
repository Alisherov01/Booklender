package com.example.Library.controller;

import com.example.Library.dto.BookDTO;
import com.example.Library.entity.Book;
import com.example.Library.service.BookService;
import com.example.Library.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LibraryController {
    LibraryService libraryService;
    BookService bookService;
    @PostMapping("/issueBook/{readerId}/{bookId}")
    public String issueBook(@PathVariable Long readerId,@PathVariable Long bookId) {
        return libraryService.issueOfTheBook(readerId,bookId);
    }
    @PostMapping ("/returnBook/{readerId}/{bookId}")
    public String returnBook(@PathVariable Long readerId,@PathVariable Long bookId) {
        return libraryService.returnOfTheBook(readerId,bookId);
    }
    @GetMapping("/AllBooks")
    public List<BookDTO> findAllBook(){
        return bookService.findAllBooks();
    }
    @GetMapping("/all")
    public List<Book> findAll() {
        return bookService.findAll();
    }
}
