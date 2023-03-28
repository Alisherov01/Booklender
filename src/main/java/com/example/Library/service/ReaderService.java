package com.example.Library.service;

import com.example.Library.entity.Reader;
import com.example.Library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public Reader getById(Long id) {
        Optional<Reader> reader = readerRepository.findById(id);
        Reader reader1 = new Reader();
        reader1.setId(reader.get().getId());
        reader1.setFullName(reader.get().getFullName());
        reader1.setEmail(reader.get().getEmail());
        reader1.setUserName(reader.get().getUserName());
        reader1.setPassword(reader.get().getPassword());
        reader1.setAuthStatus(reader.get().getAuthStatus());

        return reader1;
    }

    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader update(Reader reader) {
        Reader reader1 = readerRepository.findById(reader.getId()).get();
        reader1.setId(reader.getId());
        reader1.setFullName(reader.getFullName());
        reader1.setEmail(reader.getEmail());
        reader1.setUserName(reader.getUserName());
        reader1.setPassword(reader.getPassword());
        reader1.setAuthStatus(reader.getAuthStatus());
        //reader1.setBooks(reader.getBooks());
        return readerRepository.save(reader1);
    }
    public String delete (Long id) {
        readerRepository.deleteById(id);
        return "Delete";

    }
}
