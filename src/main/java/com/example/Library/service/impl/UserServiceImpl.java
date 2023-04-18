package com.example.Library.service.impl;


import com.example.Library.dto.UserDTO;
import com.example.Library.dto.UserDTOforList;
import com.example.Library.dto.UserSaveDTO;
import com.example.Library.entity.User;
import com.example.Library.enums.Roles;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookServiceImpl bookServiceImpl;

    private PasswordEncoder passwordEncoder;

    private UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getLogin(),
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
    public UserSaveDTO saveForReg(UserSaveDTO dto){

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
