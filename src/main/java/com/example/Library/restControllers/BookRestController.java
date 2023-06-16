package com.example.Library.restControllers;

import com.example.Library.config.AuthenticationRequest;
import com.example.Library.config.AuthneticationResponse;
import com.example.Library.dto.BookDTO;
import com.example.Library.entity.Book;
import com.example.Library.filter.JWTUtil;
import com.example.Library.service.BookService;
import com.example.Library.service.impl.BookServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookRestController {
    private final BookService bookService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

//    @GetMapping("/login/oauth21")
//    public String redirectToAuthorizationEndpoint1() {
//        return "redirect:/login/oauth2/code/google";
//    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthneticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect user or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthneticationResponse(jwt));
    }
    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/all")
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/update/{id}")
    public BookDTO update(@PathVariable Long id,
            @RequestBody BookDTO book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("create")
    public BookDTO create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @GetMapping("/getFree")
    public List<Book> getAllFreeBook() {
        return bookService.getFreeBooks();
    }

    @GetMapping("/getReadingBooksByUserId/{id}")
    public List<Book> getReadingUserBooks(@PathVariable Long id) {
        return bookService.getReadingBooksByUserId(id);
    }

    @GetMapping("/getAllBooksByUserId/{id}")
    public List<String> getAllUserBooks(@PathVariable Long id) {
        return bookService.getAllBooksByUserId(id);
    }


}
