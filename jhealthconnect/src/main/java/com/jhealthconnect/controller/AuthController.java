package com.jhealthconnect.controller;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔹 Show Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Thymeleaf template (login.html)
    }

    // 🔹 Show Registration Page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Thymeleaf template (register.html)
    }

    // 🔹 Handle Registration Form
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        // Check if username/email already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already taken!");
            return "register";
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role and status
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        user.setActive(true);

        // Save user
        userRepository.save(user);

        // Redirect to login after successful registration
        return "redirect:/login?success";
    }
}
