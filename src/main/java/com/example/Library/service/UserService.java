package com.example.Library.service;


import com.example.Library.dto.UserDTO;
import com.example.Library.dto.UserDTO;
import com.example.Library.dto.UserDTOforList;
import com.example.Library.entity.User;
import com.example.Library.entity.User;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.BorrowingRepository;
import com.example.Library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookService bookService;

    private UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword(),
                user.getAuthStatus(),
                user.getBorrowings());
    }

    public UserDTO getById(Long id) {
        User user = userRepository.findById(id).get();
        return toDto(user);
    }

    public User getByIdEntity(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }


    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user: users) {
            UserDTO dto = toDto(user);
            userDTOS.add(dto);
        }
        return userDTOS;
    }

    //??
    public UserDTO create(User user) {
        return toDto(userRepository.save(user));
    }

    //??-----
    public UserDTO update(User user) {
        return toDto(userRepository.save(user));
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id).get();
        user.setRemoveDate(LocalDate.now());
        userRepository.save(user);
    }

    //список всех пользователей со списком их книг
    public List<UserDTOforList> getAllBooksOfAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTOforList> usersDto = new ArrayList<>();

        for (User user: users) {
            UserDTOforList userDTo = new UserDTOforList();
            userDTo.setFullName(user.getFullName());
            List<String> books = bookService.getAllBooksByUserId(user.getId());
            userDTo.setBooks(books);
            usersDto.add(userDTo);
        }
        return usersDto;
    }

}
