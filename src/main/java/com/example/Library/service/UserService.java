package com.example.Library.service;

import com.example.Library.dto.UserDto;
import com.example.Library.dto.UserForListDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto create(UserDto user);
    UserDto update(Long id, UserDto dto);
    void deleteById(Long id);
    List<UserForListDto> getAllBooksOfAllUsers();

}
