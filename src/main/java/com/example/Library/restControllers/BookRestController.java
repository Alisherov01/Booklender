package com.example.Library.restControllers;

import com.example.Library.dto.BookDto;
import com.example.Library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookRestController {
    private final BookService bookService;
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/all")
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/update/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody BookDto book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("/create")
    public BookDto create(@RequestBody BookDto dto) {
        return bookService.create(dto);
    }

    @GetMapping("/getFree")
    public List<BookDto> getAllFreeBook() {
        return bookService.getFreeBooks();
    }

    @GetMapping("/getReadingBooksByUserId/{id}")
    public List<String> getReadingUserBooks(@PathVariable Long id) {
        return bookService.getReadingBooksByUserId(id);
    }

    @GetMapping("/getAllBooksByUserId/{id}")
    public List<String> getAllUserBooks(@PathVariable Long id) {
        return bookService.getAllBooksByUserId(id);
    }


}
