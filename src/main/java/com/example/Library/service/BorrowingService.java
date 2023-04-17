package com.example.Library.service;

import com.example.Library.dto.BorrowingDto;
import org.springframework.stereotype.Component;

@Component
public interface BorrowingService {
    BorrowingDto takeBook(Long userId, Long bookId);
    BorrowingDto returnBook(Long userId, Long bookId);

}
