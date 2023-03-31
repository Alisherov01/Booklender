package com.example.Library.repository;

import com.example.Library.entity.Book;
import com.example.Library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /*@Query("select fullName, borrowings from User")
    List<User> getAllBooks();*/
}
