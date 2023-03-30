package com.example.Library.security;

import com.example.Library.entity.Reader;
import com.example.Library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    ReaderRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader user = userRepository.findByLogin(username);
        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),user.getRole().getAuthorities());
    }
}
