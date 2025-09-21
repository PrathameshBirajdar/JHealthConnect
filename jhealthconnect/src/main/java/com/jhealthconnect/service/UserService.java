package com.jhealthconnect.service;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.entity.UserType;
import com.jhealthconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    public User findByEmailAndUserType(String email, UserType userType) {
        return userRepository.findByEmailAndUserType(email, userType);
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public List<User> searchUsers(String searchTerm) {
        return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(searchTerm, searchTerm);
    }
    
    public List<User> findByUserType(UserType userType) {
        return userRepository.findByUserType(userType);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public long getActiveUserCount() {
        return userRepository.countByIsActiveTrue();
    }
    
    public long getNewUsersThisMonth() {
        return userRepository.countNewUsersThisMonth();
    }
}