package com.jhealthconnect.controller;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.entity.UserSettings;
import com.jhealthconnect.repository.UserRepository;
import com.jhealthconnect.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public abstract class BaseController {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserSettingsRepository userSettingsRepository;

    protected void addUserSettingsToModel(Long userId, Model model) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            UserSettings settings = userSettingsRepository.findByUser(user);
            if (settings != null) {
                model.addAttribute("darkmode", settings.isDarkmode());
            }
        }
    }
}
