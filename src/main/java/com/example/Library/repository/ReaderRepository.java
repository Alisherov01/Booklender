package com.example.Library.repository;

import com.example.Library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,Long> {

    Reader findByLogin(String login);
}
