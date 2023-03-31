package com.example.Library.service;

import com.example.Library.dto.ReaderDTO;
import com.example.Library.entity.Reader;
import com.example.Library.repository.ReaderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {
    ReaderRepository readerRepository;

    EmailService emailService;

    public List<Reader> getAll() throws DataAccessException {
        try {
            return readerRepository.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to get all Readers", e){};
        }
    }

    public Reader getById(Long id) throws DataAccessException {
        try {
            return readerRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to get user with id " + id, e){};
        }
    }

    public Reader save(Reader reader) throws DataAccessException {
       try {
           return readerRepository.save(reader);
       }catch (DataAccessException e) {
           throw new DataAccessException("Failed to create user", e){};       }
    }

    public Reader update(Reader reader) throws DataAccessException {
        try {
           return readerRepository.save(reader);

        } catch (OptimisticLockException ex) {
            throw new OptimisticLockException("Error occurred while updating user: " + ex.getMessage()) {};
        }
    }
    public String  delete (Long id) throws Exception {
        try {
            readerRepository.deleteById(id);
            return "Reader delete";
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to delete object with id " + id, e) {
            };
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Object with id " + id + " not found", e);
        } catch (Exception e) {
            throw new Exception("Failed to delete object with id " + id, e);
        }
    }
    public ReaderDTO registration(ReaderDTO readerDTO){
        Reader reader = new Reader();
        reader.setFullName(readerDTO.getName());
        reader.setEmail(readerDTO.getEmail());
        reader.setLogin(readerDTO.getLogin());
        reader.setPassword(readerDTO.getPassword());
        readerRepository.save(reader);
        Long id = reader.getId();
        emailService.sendEmail(reader.getEmail(),"Welcome","http://localhost:8080/email/simple-email/verification/{id}"+id);
        return readerDTO;
    }
}
