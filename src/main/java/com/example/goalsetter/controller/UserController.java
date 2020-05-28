package com.example.goalsetter.controller;

import com.example.goalsetter.error.ResourceNotFoundException;
import com.example.goalsetter.pojo.User;
import com.example.goalsetter.security.AuthenticationRequest;
import com.example.goalsetter.security.AuthenticationResponse;
import com.example.goalsetter.security.JwtUtils;
import com.example.goalsetter.service.JwtUserDetailsService;
import com.example.goalsetter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController() { }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);

        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest authRequest) {
        String password = authRequest.getPassword();
        String email = authRequest.getEmail();

        Optional<User> potentialUser = userService.getUserByEmail(email);

        if(potentialUser.isPresent() && password.equals(potentialUser.get().getPassword())) {
            String token = jwtUtils.generateToken(potentialUser.get());

            return new AuthenticationResponse(token);
        } else {
            throw new ResourceNotFoundException("Email or password is not correct");
        }
    }
}
