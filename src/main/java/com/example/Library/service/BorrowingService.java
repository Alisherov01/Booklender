package com.example.Library.service;

import com.example.Library.dto.BorrowingDTO;
import com.example.Library.entity.Borrowing;
import com.example.Library.repository.BorrowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final UserService userService;
    private final BookService bookService;

    private BorrowingDTO toDto(Borrowing borrowing){
        return new BorrowingDTO(
                borrowing.getId(),
                borrowing.getUser(),
                borrowing.getBook(),
                borrowing.getTakeData(),
                borrowing.getReturnDate()
        );
    }

    public BorrowingDTO takeBook(Long userId, Long bookId) {
        if (borrowingRepository.countBookByUserId(userId) <= 2){
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(userService.getByIdEntity(userId));
        borrowing.setBook(bookService.getByIdEntity(bookId));
        borrowingRepository.save(borrowing);
        return toDto(borrowing);
    } else return null;
    }

    public BorrowingDTO returnBook(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id).get();
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
        return toDto(borrowing);
    }

    public int countBooksByUserId(Long id) {
        return borrowingRepository.countBookByUserId(id);
    }

}
