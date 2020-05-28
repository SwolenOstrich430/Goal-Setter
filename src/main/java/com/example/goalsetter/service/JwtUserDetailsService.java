package com.example.goalsetter.service;

import com.example.goalsetter.pojo.User;
import com.example.goalsetter.repository.UserRepository;
import com.example.goalsetter.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println(username);
        Optional<User> user = userRepository.findUserByUsername(username);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return new JwtUserDetails(user.get());



    }


}
