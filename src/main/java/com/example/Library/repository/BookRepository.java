package com.example.Library.repository;

import com.example.Library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAllByGenre(String genre);
    Book findByNameOfBook(String name);
}
