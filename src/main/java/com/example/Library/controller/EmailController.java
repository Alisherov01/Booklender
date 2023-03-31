package com.example.Library.controller;
import com.example.Library.dto.ReaderDTO;
import com.example.Library.entity.Reader;
import com.example.Library.enums.AuthStatus;
import com.example.Library.repository.ReaderRepository;
import com.example.Library.service.EmailService;
import com.example.Library.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

    @Autowired
    EmailService emailService;
    @Autowired
    ReaderRepository readerRepository;

    ReaderService readerService;


    @GetMapping(value = "/simple-email/{user-email}")
    public void sendEmail(@PathVariable("user-email") String email){
        emailService.sendEmail(email,"Добро пожаловать!","/verification/{id}");
    }

    @PutMapping(value = "/verification/{id}")
    public void verification(@PathVariable Long id){
        Reader reader = readerRepository.findById(id).get();
        reader.setAuthStatus(AuthStatus.ACTIVE);
        readerRepository.save(reader);
        System.out.println("Добро пожаловать! "+reader.getFullName());
    }

    @PostMapping("/registration")
    ReaderDTO registration(@RequestBody ReaderDTO readerDTO){
        return readerService.registration(readerDTO);
    }
}
