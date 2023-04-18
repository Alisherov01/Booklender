package com.example.Library.repository;

import com.example.Library.entity.Book;
import com.example.Library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //все книги пользователя которые он читал или читает сейчас
    @Query(value = "select distinct name from books join borrowings on books.id = borrowings.book_id where borrowings.user_id = ?1", nativeQuery = true)
    List<String> getAllBooksByUserId(Long userId);

    //книги пользователя, которые он читает (но еще не вернул обратно)
    @Query(value = "select distinct name from books join borrowings on books.id = borrowings.book_id where borrowings.user_id = ?1 and borrowings.return_date is null", nativeQuery = true)
    List<String> getReadingBooksByUserId(Long userId);

    User findByLogin(String login);
}
