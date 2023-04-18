package com.example.Library.service;

import com.example.Library.dto.UserDTO;
import com.example.Library.dto.UserDTOforList;
import com.example.Library.dto.UserSaveDTO;
import com.example.Library.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    UserDTO getById(Long id);
    User getByIdEntity(Long id);
    List<UserDTO> getAll();
    UserDTO create(User user);
    UserDTO update(User user);
    void deleteById(Long id);
    List<UserDTOforList> getAllBooksOfAllUsers();
    UserSaveDTO saveForReg(UserSaveDTO dto);

}
