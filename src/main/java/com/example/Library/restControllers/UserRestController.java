package com.example.Library.restControllers;

import com.example.Library.entity.User;
import com.example.Library.repository.BorrowingRepository;
import com.example.Library.service.BookService;
import com.example.Library.service.BorrowingService;
import com.example.Library.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestController {
    private final UserService userService;



}
