package com.example.Library.repository;

import com.example.Library.entity.Book;
import com.example.Library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

   /* List<Book> findAllByGenre(String genre);
    Book findByNameOfBook(String name);*/

    //книги, которые заняты
    @Query(value = "select name from books join borrowings on books.id = borrowings.book_id where return_date is null", nativeQuery = true)
    List<Book> getNotFreeBooks();

    //книги, которые свободны
    @Query(value = "select * from books where books.id in (select book_id from borrowings where return_date is not null) or books.id not in (select book_id from borrowings)", nativeQuery = true)
    List<Book> getFreeBooks();

    //все книги пользователя которые он читал или читает сейчас
    @Query(value = "select * from books join borrowings b on books.id = b.book_id where user_id = ?1", nativeQuery = true)
    List<Book> getAllBooksByUserId(Long userId);

    //книги пользователя, которые он читает (но еще не вернул обратно)
    @Query(value = "select * from books join borrowings on books.id = borrowings.book_id where borrowings.user_id = ?1 and borrowings.return_date is null", nativeQuery = true)
    List<Book> getReadingBooksByUserId(Long userId);

}
