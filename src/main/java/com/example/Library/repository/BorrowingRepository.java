package com.example.Library.repository;

import com.example.Library.entity.Book;
import com.example.Library.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {

    @Query(value = "select count(book_id) from borrowings where user_id = ?1 and return_date is null", nativeQuery = true)
    int countBookByUserId(Long id);

    @Query(value = "select distinct * from borrowings where borrowings.user_id = ?1 and borrowings.book_id = ?2 and borrowings.return_date is null", nativeQuery = true)
    Borrowing findByUserIdAndBook_IdAAndAndReturnDateIsNull(Long userId, Long bookId);



}