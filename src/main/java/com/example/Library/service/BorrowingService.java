package com.example.Library.service;

import com.example.Library.dto.BorrowingDTO;
import com.example.Library.entity.Borrowing;
import org.springframework.stereotype.Component;

@Component
public interface BorrowingService {
    BorrowingDTO takeBook(Long userId, Long bookId);
    BorrowingDTO returnBook(Long id);
    int countBooksByUserId(Long id);

    Borrowing returnBookByUser(Long userId,Long bookId);
}
