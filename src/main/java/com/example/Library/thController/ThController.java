package com.example.Library.thController;

import com.example.Library.entity.Book;
import com.example.Library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ThController {
    BookService bookService;

    @GetMapping("/allBooks")
    public String allBooks(Model model)throws Exception{
        List<Book> books = bookService.findAll();
        model.addAttribute("books",books);
        return "allBooks";

    }
    @GetMapping("/main")
    public String main()throws Exception{
        return "library";
    }
}
