package com.example.Library.service;

import com.example.Library.entity.History;
import com.example.Library.repository.HistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoryService {
    HistoryRepository historyRepository;
    public History getById(Long id) {
        try {
            return historyRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("History with id " + id + " not found"));
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to get history with id " + id, e){};
        }
    }
}
