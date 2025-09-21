package com.jhealthconnect.controller;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.entity.UserSettings;
import com.jhealthconnect.repository.UserRepository;
import com.jhealthconnect.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSettingsRepository userSettingsRepository;

    @GetMapping("/{userId}")
    public String getSettings(@PathVariable Long userId, Model model) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            model.addAttribute("error", "User not found!");
            return "error";
        }

        UserSettings settings = userSettingsRepository.findByUser(user);
        if (settings == null) {
            settings = new UserSettings();
            settings.setUser(user);
        }

        model.addAttribute("settings", settings);
        return "settings";
    }

    @PostMapping("/update/{userId}")
    public String updateSettings(@PathVariable Long userId, @ModelAttribute UserSettings updatedSettings) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/error";
        }

        updatedSettings.setUser(user);
        userSettingsRepository.save(updatedSettings);

        return "redirect:/settings/" + userId;
    }
}
