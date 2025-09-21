package com.jhealthconnect.controller;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public String getProfile(@PathVariable Long userId, Model model) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            model.addAttribute("error", "User not found!");
            return "error";
        }

        model.addAttribute("user", user);
        addUserSettingsToModel(userId, model);
        return "profile";
    }

    @PostMapping("/update/{userId}")
    public String updateProfile(@PathVariable Long userId, @ModelAttribute User updatedUser) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/error";
        }

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword());
        }

        userRepository.save(user);
        return "redirect:/profile/" + userId;
    }
}
