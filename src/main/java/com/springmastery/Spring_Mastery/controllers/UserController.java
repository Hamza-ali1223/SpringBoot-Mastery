package com.springmastery.Spring_Mastery.controllers;

import com.springmastery.Spring_Mastery.Model.User;
import com.springmastery.Spring_Mastery.services.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    public User getUserById(@PathVariable String userName)
    {
        return userService.findByUserName(userName);
    }

    @PostMapping
    public User createUser(@RequestBody  User user)
    {
        if(null!=user)
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPass= passwordEncoder.encode(user.getPassword());
            System.out.println("We got Hashed Password: "+hashedPass);
            user.setPassword(hashedPass);
            userService.saveUser(user);
            return user;
        }
        return null;
    }
}
