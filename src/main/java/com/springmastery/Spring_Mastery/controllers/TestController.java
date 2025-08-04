package com.springmastery.Spring_Mastery.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{

    @GetMapping("/test-user")
    public String testUser()
    {
        return "You Accessed it as User or Admin!";
    }

    @GetMapping("/test-admin")
    public String testAdmin()
    {
        return "You Accessed it as Admin!";
    }

    @GetMapping("/my-authorities")
    public String getMyAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return "Your authorities: " + authentication.getAuthorities().toString();
        }
        return "Not authenticated.";
    }

}
