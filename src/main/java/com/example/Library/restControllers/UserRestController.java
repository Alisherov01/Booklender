package com.example.Library.restControllers;

import com.example.Library.dto.UserDTOforList;
import com.example.Library.service.UserService;
import com.example.Library.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/allBooksOfAllUsers")
    public List<UserDTOforList> getAllBooksOfAllUsers() {
        return userService.getAllBooksOfAllUsers();
    }


}
