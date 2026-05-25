package com.example.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.interview.entity.User;
import com.example.interview.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // REGISTER PAGE
    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    // SAVE USER
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {

        userService.registerUser(user);

        return "redirect:/login";
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    // ROLE BASED DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {

        String role = auth
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        // ADMIN DASHBOARD
        if(role.equals("ROLE_ADMIN")) {
            return "admin-dashboard";
        }

        // USER DASHBOARD
        return "user-dashboard";
    }
}