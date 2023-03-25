package com.example.Library.controller;

import com.example.Library.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LibraryController {
    LibraryService libraryService;
    @PostMapping("/issueBook/{readerId}/{bookId}")
    public String issueBook(@PathVariable Long readerId,@PathVariable Long bookId) {
        return libraryService.issueOfTheBook(readerId,bookId);
    }
    @PostMapping ("/returnBook/{readerId}/{bookId}")
    public String returnBook(@PathVariable Long readerId,@PathVariable Long bookId) {
        return libraryService.returnOfTheBook(readerId,bookId);
    }
}
