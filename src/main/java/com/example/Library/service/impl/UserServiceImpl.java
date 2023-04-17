package com.example.Library.service.impl;


import com.example.Library.dto.UserDto;
import com.example.Library.dto.UserForListDto;
import com.example.Library.entity.User;
import com.example.Library.mapper.UserMapper;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final BookServiceImpl bookServiceImpl;

    @Override

    public UserDto getById(Long id) {
        return mapper.map(userRepository.findById(id).get());
    }

    @Override
    public List<UserDto> getAll() {
        return mapper.map(userRepository.findAll());
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = userRepository.save(mapper.map(dto));
        return mapper.map(user);
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            user.setFullName(dto.getFullName());
            user.setEmail(dto.getEmail());
            user.setUserName(dto.getUserName());
            user.setPassword(dto.getPassword());
            user.setAuthStatus(dto.getAuthStatus());
            user.setBorrowings(dto.getBorrowings());

            userRepository.save(user);
        }
        return mapper.map(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).get();
        user.setRemoveDate(LocalDate.now());
        userRepository.save(user);
    }

    //список всех пользователей со списком их книг
    @Override
    public List<UserForListDto> getAllBooksOfAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserForListDto> usersDto = new ArrayList<>();

        for (User user: users) {
            UserForListDto userDTo = new UserForListDto();
            userDTo.setFullName(user.getFullName());
            List<String> books = bookServiceImpl.getAllBooksByUserId(user.getId());
            userDTo.setBooks(books);
            usersDto.add(userDTo);
        }
        return usersDto;
    }

}
