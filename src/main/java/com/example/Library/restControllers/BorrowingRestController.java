package com.example.Library.restControllers;

import com.example.Library.dto.BorrowingDTO;
import com.example.Library.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrowing")
@AllArgsConstructor
public class BorrowingRestController {
    private final BorrowingService borrowingService;

    @PostMapping("/takeBook/{userId}/{bookId}")
    public BorrowingDTO takeBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return borrowingService.takeBook(userId, bookId);
    }

    @PostMapping("/returnBook/{id}")
    public BorrowingDTO returnBook(@PathVariable Long id) {
        return borrowingService.returnBook(id);
    }
}
