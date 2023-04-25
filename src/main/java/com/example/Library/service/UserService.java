package com.example.Library.service;

import com.example.Library.dto.UserDto;
import com.example.Library.dto.UserForListDto;
import com.example.Library.dto.UserSaveDTO;
import com.example.Library.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto create(UserDto dto);
    UserDto update(Long id, UserDto dto);
    void deleteById(Long id);
    List<UserForListDto> getAllBooksOfAllUsers();
    UserSaveDTO saveForReg(UserSaveDTO dto);

}
