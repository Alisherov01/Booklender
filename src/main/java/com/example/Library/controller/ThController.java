package com.example.Library.controller;

import com.example.Library.dto.BookDto;
import com.example.Library.dto.BookSaveDTO;
import com.example.Library.dto.UserSaveDTO;
import com.example.Library.entity.Book;
import com.example.Library.entity.User;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.BookService;
import com.example.Library.service.BorrowingService;
import com.example.Library.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class ThController {
    BookService bookService;
    UserService readerService;
    BorrowingService borrowingService;
    UserRepository userRepository;


    AuthenticationManager authenticationManager;

    @GetMapping("/allBooks")
    public String allBooks(Model model)throws Exception{
        List<BookDto> books = bookService.getAll();
        model.addAttribute("books",books);
        return "allBooks";

    }
    @GetMapping("/main")
    public String main()throws Exception{
        return "library";
    }
    @GetMapping("/auth")
    public String auth()throws Exception{
//
        return "authentification";
    }
    @GetMapping("/auth/success")
    public String log(){


        return "cabinet2";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("dto") User user, Model model)throws Exception{

        return "registration";
    }

    @GetMapping("/registration/save")
    public String regSave(@ModelAttribute("dto") UserSaveDTO dto, Model model) throws Exception{
        readerService.saveForReg(dto);
        model.addAttribute("readerName",dto.getFullName());
        return "regdone";
    }
    @GetMapping("/auth/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/auth/wrong")
    public String wrong(){
        return "wrong";
    }

    @GetMapping("/newBook")
    public String newBook(@ModelAttribute("dto")BookSaveDTO dto, Model model){
        return "adminAddBook";
    }

    @GetMapping("/addNewBook")
    public String addNewBook(@ModelAttribute("dto")BookSaveDTO dto, Model model){
        bookService.saveNewBook(dto);
        model.addAttribute("bookName",dto.getName());
        return "bookAdded";
    }

    @GetMapping("/cabAllBooks")
    public String cabAllBooks(Model model){
        List<BookDto> books = bookService.getFreeBooks();
        model.addAttribute("books",books);

        User user = new User();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            user = userRepository.findByLogin(userDetails.getUsername());
        }
        model.addAttribute("userIsUser2",user);

        return "cabAllBooks";
    }

    @GetMapping("/takeBook/{id2}/{id3}")
    public String takeBook(@PathVariable("id2") Long userId,
                           @PathVariable("id3") Long bookId,
                           Model model){
//        User user = new User();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            UserDetails userDetails = (UserDetails) auth.getPrincipal();
//           user = userRepository.findByLogin(userDetails.getUsername());
//        }
//        model.addAttribute("userIsuser",user);
        borrowingService.takeBook(userId,bookId);
        return "cabAllBooks";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id,
                             Model model){
        String name = bookService.deleteById(id);
        model.addAttribute("name",name);
        return "bookDeleted";
    }

    @GetMapping("/readingBooks")
    public String readingBooks(
                               Model model){
        User user = new User();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            user = userRepository.findByLogin(userDetails.getUsername());
        }
        Long id2 = user.getId();
        List<Book> userBooks = bookService.getReadingBooksByUserId(id2);
        model.addAttribute("userForReturn",user);
        model.addAttribute("userBooks",userBooks);
        return "readingBooks";
    }

    @GetMapping("/returnBook/{userId}/{bookId}")
    public String returnBook(@PathVariable("userId") Long userId,
                             @PathVariable("bookId") Long bookId){
        borrowingService.returnBook(userId,bookId);
        return "cabinet2";
    }


}
