package com.example.Library.restControllers;

import com.example.Library.dto.BorrowingDto;
import com.example.Library.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrowing")
@AllArgsConstructor
public class BorrowingRestController {
    private final BorrowingService borrowingService;

    @PostMapping("/takeBook/{userId}/{bookId}")
    public BorrowingDto takeBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return borrowingService.takeBook(userId, bookId);
    }

    @PostMapping("/returnBook/{userId}/{bookId}")
    public BorrowingDto returnBook(@PathVariable Long userId,@PathVariable Long bookId) {
        return borrowingService.returnBook(userId, bookId);
    }
}
