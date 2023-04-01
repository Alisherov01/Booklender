package com.example.Library.service.impl;


import com.example.Library.dto.UserDTO;
import com.example.Library.dto.UserDTOforList;
import com.example.Library.entity.User;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.UserService;
import com.example.Library.service.impl.BookServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookServiceImpl bookServiceImpl;

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
    @Override

    public UserDTO getById(Long id) {
        User user = userRepository.findById(id).get();
        return toDto(user);
    }

    @Override
    public User getByIdEntity(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }


    @Override
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
    @Override
    public UserDTO create(User user) {
        return toDto(userRepository.save(user));
    }

    //??-----
    @Override
    public UserDTO update(User user) {
        return toDto(userRepository.save(user));
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).get();
        user.setRemoveDate(LocalDate.now());
        userRepository.save(user);
    }

    //список всех пользователей со списком их книг
    @Override
    public List<UserDTOforList> getAllBooksOfAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTOforList> usersDto = new ArrayList<>();

        for (User user: users) {
            UserDTOforList userDTo = new UserDTOforList();
            userDTo.setFullName(user.getFullName());
            List<String> books = bookServiceImpl.getAllBooksByUserId(user.getId());
            userDTo.setBooks(books);
            usersDto.add(userDTo);
        }
        return usersDto;
    }

}
