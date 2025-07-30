package com.springmastery.Spring_Mastery.services;


import com.springmastery.Spring_Mastery.Model.User;
import com.springmastery.Spring_Mastery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

@Autowired
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id)
    {
        return userRepository.findById(id);

    }

    public User findByUserName(String userName)
    {
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isPresent())
        {
            return user.get();
        }
        return null;
    }

    public User saveUser(User user)
    {
        if(null!=user)
        {
            if(userRepository.findByUserName(user.getUsername()).isEmpty())
            {

                return userRepository.save(user);
            }

        }
        else
        {
            System.err.println("User Received Param is Null");
            return null;
        }
        return null;
    }
}
