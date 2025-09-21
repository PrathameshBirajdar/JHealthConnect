package com.jhealthconnect.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_settings")
public class UserSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean darkmode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ---- Getters & Setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isDarkmode() { return darkmode; }
    public void setDarkmode(boolean darkmode) { this.darkmode = darkmode; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
