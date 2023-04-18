package com.example.Library.security;

import com.example.Library.entity.User;
import com.example.Library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
//        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),user.getRole().getAuthorities());
        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),user.getRole().getAuthorities());
    }
}
