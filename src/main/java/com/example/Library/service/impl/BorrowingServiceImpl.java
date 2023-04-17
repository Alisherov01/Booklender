package com.example.Library.service.impl;

import com.example.Library.dto.BorrowingDto;
import com.example.Library.entity.Borrowing;
import com.example.Library.mapper.BorrowingMapper;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.BorrowingRepository;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowingMapper mapper;

    @Override
    public BorrowingDto takeBook(Long userId, Long bookId) {
        if (borrowingRepository.countBookByUserId(userId) < 2 &&
            bookRepository.bookIsFree(bookId) == true) {
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(userRepository.findById(userId).get());
        borrowing.setBook(bookRepository.findById(bookId).get());
        borrowingRepository.save(borrowing);
        return mapper.map(borrowing);
    } else return null;
    }

    @Override
    public BorrowingDto returnBook(Long userId, Long bookId) {
        Borrowing borrowing = borrowingRepository.findByUserIdAndBookId(userId, bookId);
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
        return mapper.map(borrowing);
    }

}
