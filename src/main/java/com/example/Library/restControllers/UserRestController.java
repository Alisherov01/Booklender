package com.example.Library.restControllers;

import com.example.Library.dto.UserForListDto;
import com.example.Library.service.UserService;
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
    public List<UserForListDto> getAllBooksOfAllUsers() {
        return userService.getAllBooksOfAllUsers();
    }


}
