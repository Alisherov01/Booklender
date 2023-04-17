package com.example.Library.mapper;

import com.example.Library.dto.UserDto;
import com.example.Library.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserDto dto);
    UserDto map(User entity);
    List<UserDto> map(List<User> entities);


}
