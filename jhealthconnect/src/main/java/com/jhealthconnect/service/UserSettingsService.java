package com.jhealthconnect.service;

import org.springframework.stereotype.Service;

import com.jhealthconnect.entity.UserSettings;
import com.jhealthconnect.repository.UserSettingsRepository;

@Service
public class UserSettingsService {
    private final UserSettingsRepository settingsRepository;

    public UserSettingsService(UserSettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public UserSettings getSettings(Long userId) {
        return settingsRepository.findById(userId).orElse(null);
    }

    public UserSettings saveSettings(UserSettings settings) {
        return settingsRepository.save(settings);
    }
}
