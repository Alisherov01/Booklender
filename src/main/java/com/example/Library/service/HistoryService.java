package com.example.Library.service;

import com.example.Library.entity.History;
import com.example.Library.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoryService {
    HistoryRepository historyRepository;
    public History getById(Long id) {
        Optional<History> history = historyRepository.findById(id);
        return history.orElse(new History());
    }
}
