package com.springmastery.Spring_Mastery.services;

import com.springmastery.Spring_Mastery.Model.User;
import com.springmastery.Spring_Mastery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    public final UserRepository userRepository;

    public CustomUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUserName(username);
        if(user.isPresent())
        {
            System.out.println("We got User in LoadUserByUsername: "+user.get().toString());
                return user.get();

        }
        return null;
    }
}
