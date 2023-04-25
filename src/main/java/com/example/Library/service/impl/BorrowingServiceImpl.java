package com.example.Library.service.impl;

import com.example.Library.dto.BookDto;
import com.example.Library.dto.BorrowingDto;
import com.example.Library.entity.Book;
import com.example.Library.entity.Borrowing;
import com.example.Library.enums.Status;
import com.example.Library.mapper.BookMapper;
import com.example.Library.mapper.BorrowingMapper;
import com.example.Library.mapper.UserMapper;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.BorrowingRepository;
import com.example.Library.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final UserServiceImpl userServiceImpl;
    private final BookServiceImpl bookServiceImpl;
    private final BorrowingMapper borrowingMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserMapper userMapper;

    @Override
    public BorrowingDto takeBook(Long userId, Long bookId) {
        if (borrowingRepository.countBookByUserId(userId) < 2 &&
                bookRepository.bookIsFree(bookId)) {
            Borrowing borrowing = new Borrowing();

            BookDto book = bookServiceImpl.getById(bookId);
            book.setStatus(Status.NOT_AVAILABLE);
            bookServiceImpl.update(bookId, book);

            borrowing.setUser(userMapper.map(userServiceImpl.getById(userId)));
            borrowing.setBook(bookMapper.map(bookServiceImpl.getById(bookId)));
            borrowingRepository.save(borrowing);
            return borrowingMapper.map(borrowing);
        } else return null;
    }

    @Override
    public BorrowingDto returnBook(Long userId, Long bookId) {
        Borrowing borrowing = borrowingRepository.findByUserIdAndBookId(userId, bookId);
        borrowing.setReturnDate(LocalDate.now());

        BookDto book = bookServiceImpl.getById(bookId);
        book.setStatus(Status.AVAILABLE);
        bookServiceImpl.update(bookId, book);

        borrowingRepository.save(borrowing);
        return borrowingMapper.map(borrowing);
    }
}
