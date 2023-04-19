package com.example.Library.restControllers;

import com.example.Library.dto.BookDTO;
import com.example.Library.entity.Book;
import com.example.Library.service.BookService;
import com.example.Library.service.impl.BookServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookRestController {
    private final BookService bookService;
    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/all")
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/update/{id}")
    public BookDTO update(@PathVariable Long id,
            @RequestBody BookDTO book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("create")
    public BookDTO create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @GetMapping("/getFree")
    public List<Book> getAllFreeBook() {
        return bookService.getFreeBooks();
    }

    @GetMapping("/getReadingBooksByUserId/{id}")
    public List<Book> getReadingUserBooks(@PathVariable Long id) {
        return bookService.getReadingBooksByUserId(id);
    }

    @GetMapping("/getAllBooksByUserId/{id}")
    public List<String> getAllUserBooks(@PathVariable Long id) {
        return bookService.getAllBooksByUserId(id);
    }


}
