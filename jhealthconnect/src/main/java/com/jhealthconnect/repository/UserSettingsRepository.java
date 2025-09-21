package com.jhealthconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.entity.UserSettings;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
    UserSettings findByUser(User user);
}
