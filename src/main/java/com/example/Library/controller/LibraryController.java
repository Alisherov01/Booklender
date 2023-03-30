package com.example.Library.controller;

import com.example.Library.dto.BookDTO;
import com.example.Library.entity.Book;
import com.example.Library.entity.Library;
import com.example.Library.entity.Reader;
import com.example.Library.service.BookService;
import com.example.Library.service.LibraryService;
import com.example.Library.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LibraryController {
    LibraryService libraryService;
    BookService bookService;
    ReaderService readerService;
    @PostMapping("/issueBook/{readerId}/{bookId}")
    public String issueBook(@PathVariable Long readerId,@PathVariable Long bookId) {
        return libraryService.issueOfTheBook(readerId,bookId);
    }
    @PostMapping ("/returnBook/{readerId}/{bookId}")
    public String returnBook(@PathVariable Long readerId,@PathVariable Long bookId) {
        return libraryService.returnOfTheBook(readerId,bookId);
    }
    @GetMapping("/all")
    public List<Book> findAll() {
        return bookService.findAll();
    }
    @PostMapping("book/save")
    public Book save(@RequestBody Book book){
        return bookService.save(book);
    }
    @PostMapping("reader/save")
    public Reader save (@RequestBody Reader reader) {
        return readerService.save(reader);
    }
    @PostMapping("library/save")
    public Library save(@RequestBody Library library) {
        return libraryService.save(library);
    }
}
