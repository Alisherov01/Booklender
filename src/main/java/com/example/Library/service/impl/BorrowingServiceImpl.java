package com.example.Library.service.impl;

import com.example.Library.dto.BorrowingDTO;
import com.example.Library.entity.Borrowing;
import com.example.Library.repository.BorrowingRepository;
import com.example.Library.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final UserServiceImpl userServiceImpl;
    private final BookServiceImpl bookServiceImpl;

    private BorrowingDTO toDto(Borrowing borrowing){
        return new BorrowingDTO(
                borrowing.getId(),
                borrowing.getUser().getLogin(),
                borrowing.getBook().getName(),
                borrowing.getTakeData(),
                borrowing.getReturnDate()
        );
    }

    @Override
    public BorrowingDTO takeBook(Long userId, Long bookId) {
        if (borrowingRepository.countBookByUserId(userId) <= 2){
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(userServiceImpl.getByIdEntity(userId));
        borrowing.setBook(bookServiceImpl.getByIdEntity(bookId));
        borrowingRepository.save(borrowing);
        return toDto(borrowing);
    } else return null;
    }

    @Override
    public BorrowingDTO returnBook(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id).get();
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
        return toDto(borrowing);
    }

    @Override
    public Borrowing returnBookByUser(Long userId, Long bookId) {
        Borrowing borrowing = borrowingRepository.findByUserIdAndBook_IdAAndAndReturnDateIsNull(userId,bookId);
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
        return borrowing;
    }

    @Override
    public int countBooksByUserId(Long id) {
        return borrowingRepository.countBookByUserId(id);
    }

}
