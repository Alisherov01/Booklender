package com.example.Library.service.impl;

import com.example.Library.dto.UserDto;
import com.example.Library.dto.UserForListDto;
import com.example.Library.dto.UserSaveDTO;
import com.example.Library.entity.User;
import com.example.Library.enums.Roles;
import com.example.Library.mapper.UserMapper;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookServiceImpl bookServiceImpl;
    private final UserMapper mapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).get();
        if (user.getRemoveDate() == null) {
            return mapper.map(user);
        }
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getRemoveDate() == null)
                .collect(Collectors.toList());
        return mapper.map(users);
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
            //user.setUserName(dto.getUserName());
            user.setPassword(dto.getPassword());
            user.setAuthStatus(dto.getAuthStatus());
            user.setBorrowings(dto.getBorrowings());

            userRepository.save(user);
        }
        return mapper.map(user);
    }

    @Override
    public UserSaveDTO saveForReg(UserSaveDTO dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Roles.USER);
        userRepository.save(user);
        return dto;
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

        for (User user : users) {
            UserForListDto userDTo = new UserForListDto();
            userDTo.setFullName(user.getFullName());
            List<String> books = bookServiceImpl.getAllBooksByUserId(user.getId());
            userDTo.setBooks(books);
            usersDto.add(userDTo);
        }
        return usersDto;
    }

}
