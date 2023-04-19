package com.example.Library.service.impl;

import com.example.Library.dto.BookDTO;
import com.example.Library.dto.BorrowingDTO;
import com.example.Library.entity.Book;
import com.example.Library.entity.Borrowing;
import com.example.Library.enums.Status;
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

    private BorrowingDTO toDto(Borrowing borrowing){
        return new BorrowingDTO(
                borrowing.getId(),
                borrowing.getUser().getLogin(),
                borrowing.getBook().getName(),
                borrowing.getTakeData(),
                borrowing.getReturnDate()
        );
    }
    private BookDTO toDtoBook(Book book) {
        return new BookDTO(
                book.getId(),
                book.getName(),
                book.getDescription(),
                book.getAuthor(),
                book.getVendorCode(),
                book.getGenre(),
                book.getStatus(),
                book.getBorrowings());
    }

    @Override
    public BorrowingDTO takeBook(Long userId, Long bookId) {
        if (borrowingRepository.countBookByUserId(userId) <= 2){
        Borrowing borrowing = new Borrowing();

        Book book = bookServiceImpl.getByIdEntity(bookId);
        book.setStatus(Status.NOT_AVAILABLE);
        bookServiceImpl.update(bookId,toDtoBook(book));

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
        List<Borrowing> borrowings = borrowingRepository.findByUserIdAndBook_Id(userId,bookId);
        Borrowing borrowing2 = new Borrowing();
        //привет это должно быть в репозитории))
        for(Borrowing borrowing: borrowings){
            if(borrowing.getReturnDate()==null){
                borrowing2 = borrowing;
            }
        }
        borrowing2.setReturnDate(LocalDate.now());

        Book book = bookServiceImpl.getByIdEntity(bookId);
        book.setStatus(Status.AVAILABLE);
        bookServiceImpl.update(bookId,toDtoBook(book));

        borrowingRepository.save(borrowing2);
        return borrowing2;
    }

    @Override
    public int countBooksByUserId(Long id) {
        return borrowingRepository.countBookByUserId(id);
    }

}
