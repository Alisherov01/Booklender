package com.example.Library.controller;

import com.example.Library.dto.*;
import com.example.Library.entity.Book;
import com.example.Library.entity.User;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.BookService;
import com.example.Library.service.BorrowingService;
import com.example.Library.service.DefaultEmailService;
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
    UserService userService;


    AuthenticationManager authenticationManager;
    DefaultEmailService emailService;

    @GetMapping("/allBooks")
    public String allBooks(Model model)throws Exception{
        List<BookDTO> books = bookService.getAll();
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
    public String log(Model model){
        User user = new User();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            user = userRepository.findByLogin(userDetails.getUsername());
        }
        model.addAttribute("userForCab",user);


        return "cabinet";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("dto") User user, Model model)throws Exception{

        return "registration";
    }

    @GetMapping("/registration/save")
    public String regSave(@ModelAttribute("dto") UserSaveDTO dto, Model model) throws Exception{
        readerService.saveForReg(dto);
        model.addAttribute("readerName",dto.getFullName());
        model.addAttribute("login",dto.getLogin());
        model.addAttribute("password",dto.getPassword());
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
        return "adminAddBook2";
    }

    @GetMapping("/addNewBook")
    public String addNewBook(@ModelAttribute("dto")BookSaveDTO dto, Model model){
        bookService.saveNewBook(dto);
        model.addAttribute("bookName",dto.getName());
        return "bookAdded";
    }

    @GetMapping("/cabAllBooks")
    public String cabAllBooks(Model model){
        List<Book> books = bookService.getFreeBooks();
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

        borrowingService.takeBook(userId,bookId);
        Book book = bookService.getByIdEntity(bookId);
        model.addAttribute("bookName", book.getName());
        return "bookTaken";
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
        borrowingService.returnBookByUser(userId,bookId);
        return "cabinet";
    }

    @GetMapping("/email")
    public String email(@ModelAttribute("dto")EmailDto dto,
                            Model model){
        return "email";
    }

    @GetMapping("/email/send")
    public String sendEmail(@ModelAttribute("dto")EmailDto dto,
                            Model model){
        emailService.sendSimpleEmail("azamatomurkulov01@gmail.com", dto.getSender(), dto.getMessage());
        return "email";
    }

    @GetMapping("/userList")
    public String listOfUsers(
                        Model model){
        List<UserDTOforList> usersDto = userService.getAllBooksOfAllUsers();
        model.addAttribute("users",usersDto);
        return "listOfUser";
    }

    @GetMapping("/history")
    public String historyOfBooks(
            Model model){

        User user = new User();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            user = userRepository.findByLogin(userDetails.getUsername());
        }

        List<Book> books = bookService.getAllBooksByUserIdHistory(user.getId());
        model.addAttribute("books",books);

        return "historyOfBooks";
    }


}
