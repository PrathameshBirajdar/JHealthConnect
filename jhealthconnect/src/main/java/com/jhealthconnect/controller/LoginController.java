package com.jhealthconnect.controller;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "auth";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        User savedUser = userService.registerUser(user);
        if (savedUser == null) {
            return "redirect:/?error=email";
        }
        return "redirect:/?registered";
    }
}