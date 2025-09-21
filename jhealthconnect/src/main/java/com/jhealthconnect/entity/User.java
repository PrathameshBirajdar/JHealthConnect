package com.jhealthconnect.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String password;
    private String phoneNumber;  // Add this
    
    @Enumerated(EnumType.STRING)
    private UserType userType;
    
    private boolean isActive = true;  // Changed from isActive to match DataLoader
    private LocalDateTime createdAt;
    
    // Constructors
    public User() {
        this.createdAt = LocalDateTime.now();
    }
    
    public User(String name, String email, String password, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getPhoneNumber() { return phoneNumber; }  // Add this
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }  // Add this
    
    public UserType getUserType() { return userType; }
    public void setUserType(UserType userType) { this.userType = userType; }
    
    public boolean isActive() { return isActive; }  // Keep this method name
    public void setActive(boolean active) { isActive = active; }  // Keep this method name
    public void setIsActive(boolean active) { isActive = active; }  // Add this for DataLoader compatibility
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}